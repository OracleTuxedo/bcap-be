# BCAP-BE Project Context

## What is this?
Spring Boot 2.7.18 REST API gateway (WAR) deployed on **WebLogic 14c (14.1.2)**.
Sits between a Next.js frontend and Oracle Tuxedo backend (200+ services via SLCFPROXY).
Uses `javax.*` namespace (not Jakarta). Java 21.

## Build & Run
```bash
.\mvnw.cmd clean package -DskipTests     # Windows (PowerShell)
./mvnw clean package -DskipTests          # Linux
```
Output: `target/bcap-2.5.war`

## Key Architecture

### Request Flow
```
Frontend -> HMAC Filter -> JWT Filter -> Audit Filter -> Controller -> Service -> Tuxedo
```

### Security Filter Chain Order
1. `HmacVerificationFilter` — HMAC-SHA256 body integrity (POST only, constant-time via `MessageDigest.isEqual()`)
2. `JwtAuthFilter` — Bearer token extraction + blacklist check
3. `AuditLogFilter` — Logs user, method, path, status, duration

### Authentication
- JWT access token: HS256, 15-minute expiry
- Refresh token: UUID, 24-hour expiry, rotation on use
- Token blacklist + rate limiter: Caffeine in-memory cache (per-JVM, not clustered)
- Public paths: `/auth/login`, `/auth/refresh`, `/actuator/health`, `/`

### Message Encryption
- AES/CBC/PKCS5Padding, 128-bit key, Base64-encoded IV per request
- Frontend encrypts -> backend decrypts -> sends to Tuxedo -> re-encrypts response

## Project Layout
```
src/main/java/
  maas/bcap/
    controller/     # REST endpoints (Auth, Message, FileManager, Bcap health)
    dto/            # Request/Response objects with @Valid annotations
    security/       # SecurityConfig, JWT, HMAC, Audit, Rate limiter, Blacklist
    service/        # Business logic (Auth, Message relay, File management)
    module/         # 200+ Tuxedo service wrappers (ac/, au/, az/, ed/, mc/, mt/)
  mti/com/
    telegram/       # Tuxedo byte-level serialization + WeblogicConnector
    cipher/         # SHA password hashing
```

## Configuration
- `application.properties` — shared config, no secrets
- `application-dev.properties` — dev secrets (uses `${ENV_VAR:default}` format, gitignored)
- `application-prod.properties` — prod secrets (gitignored)
- Profile switch: `-Dspring.profiles.active=prod`

### Key Properties
| Property | Purpose |
|----------|---------|
| `aes.secret.key` | AES encryption key (shared with frontend) |
| `jwt_secret` | JWT signing key (HS256) |
| `hmac.secret.key` | HMAC-SHA256 request signing key |
| `file.upload.path` | Base directory for file uploads |
| `cors.allowed.origins` | Comma-separated allowed origins |
| `file.ext.filter` | Allowed upload extensions (`jpg,png,pdf`) |

## Important Conventions

### Security Rules
- Never log passwords, tokens, decrypted messages, or full request/response bodies
- Log only userId for identification, sizes for debugging
- All POST endpoints require `X-HMAC-Signature` header (except public paths)
- All DTOs that receive user input must have `@Valid` on controller + `@NotBlank`/`@Size` on fields
- Use `MessageDigest.isEqual()` for security-critical string comparisons (timing-safe)
- Dev-only code must be gated with `@Profile("dev")`
- Error responses must never expose stack traces or internal details — use `GlobalExceptionHandler`

### Path Traversal
- File operations with user-supplied paths must use `buildSafeFilePath()` in `FileManagerService`
- This method normalizes paths and validates resolved path stays within base directory

### Tuxedo Integration
- `WeblogicConnector.connectTuxedo()` is the single entry point
- Uses WTC (WebLogic Tuxedo Connector) with CARRAY buffer type
- JNDI lookup: `tuxedo.services.TuxedoConnection`, service: `SLCFPROXY`
- `wls-tuxedo` dependency is `<scope>provided</scope>` — JAR comes from WebLogic runtime

### WebLogic Deployment Notes
- WAR packaging, not JAR
- Use `getServletPath()` not `getRequestURI()` for path matching (WebLogic adds context path)
- `wls-tuxedo` JAR must match WebLogic 14c version from `$WL_HOME/server/lib/`

## Docs
- `docs/CONTEXT.md` — Full frontend integration guide with API specs, changelog, migration checklist
- `docs/SECURITY_REPORT.md` — Security audit findings and remediation status
- `docs/BCAP-BE-SECURITY-FEATURES.md` — Detailed security feature documentation
- `README.md` — General project readme

## Common Tasks

### Adding a new Tuxedo service wrapper
1. Create module class under `maas/bcap/module/{category}/{serviceId}/`
2. Extend `ServiceSupport` for byte-level serialization
3. Call via `WeblogicConnector.connectTuxedo()`

### Adding a new endpoint
1. Add DTO in `dto/` with `@NotBlank`/`@Size` validation annotations
2. Add controller method with `@Valid @RequestBody`
3. HMAC filter auto-applies to all POST requests
4. JWT filter auto-applies to all non-public paths

### Security checklist for code changes
- [ ] No secrets or sensitive data in logs (use userId only, sizes for debug)
- [ ] DTOs have validation annotations, controllers use `@Valid`
- [ ] File paths sanitized via `buildSafeFilePath()` if user-supplied
- [ ] Errors return generic messages, details logged server-side only
- [ ] Dev-only code gated with `@Profile("dev")`
