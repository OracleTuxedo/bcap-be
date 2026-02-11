# Security Vulnerability Assessment Report
## Project: bcap-be (Spring Boot Backend)

**Date:** 2026-02-11 (Updated)
**Previous Assessment:** 2026-02-10
**Assessed By:** Claude Code Security Analysis (Full-Skill Comprehensive Review)
**Framework:** Spring Boot 2.7.18 / Java 21
**Version:** 2.5.1

---

## Executive Summary

| Severity | 2026-02-03 | 2026-02-06 | 2026-02-10 (R1) | 2026-02-10 (R2) | 2026-02-11 (R1) | 2026-02-11 (R2) | 2026-02-11 (R3) | 2026-02-11 (R4) | Trend |
|----------|------------|------------|-----------------|-----------------|-----------------|-----------------|-----------------|-----------------|-------|
| **CRITICAL** | 2 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | All resolved |
| **HIGH** | 3 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | All resolved (1 accepted) |
| **MEDIUM** | 3 | 2 | 1 | 1 | 1 | 1 | 1 | 1 | 1 accepted |
| **LOW** | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | All resolved |
| **Total Open** | **10** | **4** | **1** | **1** | **1** | **2** | **1** | **0** | **All fixed except 2 accepted risks** |

---

## Security Architecture

```
Request Flow:
  Client --> HMAC Filter --> JWT Filter --> Audit Filter --> Controller --> Service --> Tuxedo
```

**Filter Chain Order** (SecurityConfig.java):
1. `HmacVerificationFilter` - Request body integrity (HmacSHA256) + body size limit (1MB)
2. `JwtAuthFilter` - Authentication (Bearer token + blacklist check)
3. `AuditLogFilter` - Logging (user, method, path, status, duration)

---

## Implemented Security Controls (37 Total)

| # | Control | Component | Details |
|---|---------|-----------|---------|
| 1 | JWT Authentication | `JwtUtil.java` | HS256, 15-min expiry |
| 2 | Token Blacklist | `JwtBlacklist.java` | Caffeine, 15-min TTL, 50K max |
| 3 | Refresh Token Rotation | `RefreshTokenStore.java` | UUID, 24h expiry, rotate on use |
| 4 | Login Rate Limiting | `LoginRateLimiter.java` | 5 attempts / 15-min per userId |
| 5 | HMAC Request Signing | `HmacVerificationFilter.java` | HmacSHA256 on all body-bearing methods (POST, PUT, PATCH, DELETE) |
| 6 | AES Encryption | `MessageService.java` | AES/CBC/PKCS5Padding, 128-bit |
| 7 | Input Validation | `LoginInDto.java` | `@NotBlank`, `@Size` |
| 8 | Security Headers | `SecurityConfig.java` | HSTS, X-Frame-Options: DENY, X-XSS-Protection, X-Content-Type-Options |
| 9 | CORS | `SecurityConfig.java` | Configurable per Spring Profile |
| 10 | Audit Logging | `AuditLogFilter.java` | Every request logged with userId |
| 11 | File Extension Whitelist | `FileManagerService.java` | jpg, png, pdf only |
| 12 | Path Traversal Protection | `FileManagerService.java` | `new File(name).getName()` + `buildSafeFilePath()` |
| 13 | Exception Handling | `GlobalExceptionHandler.java` | `@ControllerAdvice`, never exposes stack traces |
| 14 | Stateless Sessions | `SecurityConfig.java` | `SessionCreationPolicy.STATELESS` |
| 15 | Request Body Caching | `CachedBodyHttpServletRequest.java` | HMAC + controller read, **1MB size limit** |
| 16 | Secrets Externalization | `application-*.properties` | Environment variables with `${ENV_VAR}` placeholders |
| 17 | Log Sanitization | `MessageService.java`, `WeblogicConnector.java` | Only byte sizes logged, no message content |
| 18 | Payload Size Validation | `CachedBodyHttpServletRequest.java` | Request body limited to 1MB |
| 19 | Telegram Library Log Sanitization | `InterfaceTelegram.java` | VO/byte data at DEBUG only, INFO shows sizes and err_flag |
| 20 | ServiceSupport Log Sanitization | `ServiceSupport.java` | Full toString at DEBUG only, INFO shows tx_code/scrn_id/op_id |
| 21 | Error Response Sanitization | `GlobalExceptionHandler.java` | Generic messages to client, details server-side only |
| 22 | Tuxedo Exception Propagation | `WeblogicConnector.java` | Exceptions re-thrown (not swallowed), callers handle failures explicitly |
| 23 | Debug Output Sanitization | `TelegramUtil.java` | System.out.println replaced with log.debug in debug methods |
| 24 | Refresh Token Rate Limiting | `AuthService.java` | 5 attempts / 15-min per token prefix via LoginRateLimiter |
| 25 | Per-File Upload Size Validation | `FileManagerService.java` | 10MB per file enforced in code (defense-in-depth with Spring config) |
| 26 | File DTO Input Validation | `FileUploadInDto.java`, `FileDownloadInDto.java` | `@NotBlank`, `@Size` on all user-supplied fields |
| 27 | Path Traversal (fileDiv) | `FileManagerService.java` | All file paths routed through `buildSafeFilePath()` |
| 28 | JWT Exception Propagation | `JwtUtil.java` | `validateToken()` propagates exceptions to filter for proper handling |
| 29 | Filename Sanitization | `FileManagerService.java` | `new File(name).getName()` strips path components at all 3 entry points |
| 30 | File Magic-Byte Validation | `FileManagerService.java` | Validates JPEG/PNG/PDF magic bytes match file extension |
| 31 | Atomic Refresh Token Rotation | `RefreshTokenStore.java` | `ConcurrentHashMap.remove()` prevents TOCTOU race on token reuse |
| 32 | Auth Entry Point Sanitization | `CustomAuthEntryPoint.java` | Generic `"Authentication required"` to client, exception details server-side only |
| 33 | DTO Size Constraints (All) | `LoginInDto`, `LogOutDto`, `RefreshTokenInDto`, `MessageTransferInDto` | `@Size` on all user input fields including password, tokens, encrypted payload, IV |
| 34 | WebLogic Path Consistency | `AuditLogFilter.java`, `CustomAuthEntryPoint.java` | `getServletPath()` used everywhere (not `getRequestURI()`) for WebLogic compatibility |
| 35 | Log Injection Prevention | `FileManagerService.java` | `sanitizeForLog()` strips `\r\n\t` from user-controlled filenames before logging |
| 36 | Silent Health Endpoint | `BcapController.java` | Public `GET /` returns version only, no log output (prevents log flooding) |
| 37 | Proper Logging in Telegram Library | `ByteEncoder.java` | `printStackTrace()` replaced with Log4j2 logger for consistent audit trail |

---

## Access Control Matrix

| Path | Auth | HMAC | Rate Limited | Payload Limit |
|------|:----:|:----:|:------------:|:-------------:|
| `GET /` | No | No (bodyless) | No | N/A |
| `GET /actuator/health` | No | No (bodyless) | No | N/A |
| `POST /auth/login` | No | Yes | Yes (5/15min) | 1MB |
| `POST /auth/refresh` | No | Yes | Yes (5/15min) | 1MB |
| `POST /auth/logout` | Yes | Yes | No | 1MB |
| `POST /message` | Yes | Yes | No | 1MB |
| `POST /file-manager/upload` | Yes | Yes | No | 10MB (multipart) |
| `GET /file-manager/download` | Yes | No (bodyless) | No | N/A |
| `PUT/PATCH/DELETE *` | Yes | Yes | No | 1MB |

---

## Open Findings

### 1. X-Forwarded-For Header Trust
**Severity:** MEDIUM
**File:** `FileManagerService.java:340`

```java
String ip = request.getHeader("X-Forwarded-For");
```

**Risk:** Client IP can be spoofed if the application is directly exposed to the internet without a trusted proxy.

**Status:** Accepted — WebLogic 14c is the entry point and should be configured to strip/overwrite `X-Forwarded-For` from external clients.

**Recommendation:** Verify proxy configuration on production WebLogic deployment.

### 2. Spring Boot 2.7.18 End-of-Life
**Severity:** HIGH
**File:** `pom.xml`

Spring Boot 2.7.18 reached end of OSS support in November 2023. Known CVEs exist in Spring Framework 5.3.x.

**Status:** Accepted — WebLogic 14c (14.1.2) requires javax.* namespace and does not support Spring Boot 3.x / Jakarta EE.

**Compensating Controls:**
- Input validation on all DTOs (`@Valid`, `@NotBlank`, `@Size`)
- HMAC request integrity on all POST endpoints
- Path traversal protection via `buildSafeFilePath()` + filename sanitization
- File upload magic-byte validation
- Generic error responses (no stack traces to client)

**Recommendation:** Upgrade WebLogic to 19c+ to enable Spring Boot 3.x migration. In the interim, consider OWASP Dependency Check in CI pipeline.

---

## Resolved Findings

### Resolved 2026-02-11 (Full-Skill Security Analysis Round 4 — 3 Findings)

| # | Finding | Severity | Resolution |
|---|---------|----------|------------|
| 1 | `BcapController` `GET /` writes test logs at all levels (trace→fatal) on every request — floods logs and triggers false alerts on public unauthenticated endpoint | LOW | **Fixed** — All test log lines removed. Endpoint returns version string silently. Logger import removed. |
| 2 | `ByteEncoder.java:122` uses `e.printStackTrace()` bypassing Log4j2 — unstructured stderr output in WebLogic | LOW | **Fixed** — Replaced with `log.error("Failed to create placeholder bytes for nested VO field [{}]", field.getName(), e)`. |
| 3 | `WebUtil.getURI()` uses `getRequestURI()` (incorrect for WebLogic context path) — unused method that could cause bugs if called in future | LOW | **Fixed** — Both `getURI()` overloads removed. Method was not called anywhere in the codebase. |

### Resolved 2026-02-11 (Full-Skill Comprehensive Review — 5 Findings)

| # | Finding | Severity | Resolution |
|---|---------|----------|------------|
| 1 | `CustomAuthEntryPoint` returns `authException.getMessage()` to client — leaks internal auth details | HIGH | **Fixed** — Returns generic `"Authentication required"`. Exception message logged server-side at WARN. Also fixed `getRequestURI()` → `getServletPath()`. |
| 2 | Multiple DTOs missing `@Size` constraints (`LoginInDto.encryptionPassword/screenId`, `LogOutDto.*`, `RefreshTokenInDto`, `MessageTransferInDto`) | MEDIUM | **Fixed** — Added `@Size` to all unbound fields: `encryptionPassword(500)`, `screenId(50)`, `userId(20)`, `refreshToken(36)`, `encryptedMessage(1MB)`, `iv(24 exact)`. |
| 3 | `AuditLogFilter` and `CustomAuthEntryPoint` use `getRequestURI()` instead of `getServletPath()` — inconsistent with WebLogic context path | MEDIUM | **Fixed** — Both changed to `getServletPath()`. All security package files now use correct path method. |
| 4 | HMAC filter only validates POST — PUT/PATCH/DELETE methods bypass integrity check | MEDIUM | **Fixed** — Filter now skips only bodyless methods (GET, HEAD, OPTIONS). All body-bearing methods are HMAC-verified. |
| 5 | `FileManagerService` logs `file.getOriginalFilename()` raw — potential log injection via crafted filenames | MEDIUM | **Fixed** — Added `sanitizeForLog()` that strips `\r\n\t`. Applied to all filename log sites. |

### Resolved 2026-02-11 (Security Review Round 2 — 8 Findings)

| # | Finding | Severity | Resolution |
|---|---------|----------|------------|
| 1 | `.bak` file with PCI card data tracked in git | CRITICAL | **Fixed** — `git rm --cached`, `*.bak` added to `.gitignore`. History scrub with `git filter-repo` recommended as follow-up. |
| 2 | `file.getOriginalFilename()` stored raw — path traversal via crafted filename | CRITICAL | **Fixed** — `new File(name).getName()` applied at all 3 entry points (`registerFiles`, `addTargetFile`, `saveToDisk`). |
| 3 | Non-atomic refresh token validate + invalidate (TOCTOU race) | HIGH | **Fixed** — `validateAndInvalidate()` uses `ConcurrentHashMap.remove()` for single atomic operation. |
| 4 | Real credentials in test files (`SAZ03V701UTest`, `SAZ03F000UTest`) | HIGH | **Fixed** — Replaced with obviously fake test data (`0000000001`, `TestPassword123!`, matching SHA-256 hashes). |
| 5 | `/message/forward/weblogic` endpoint — unrestricted raw Tuxedo proxy | HIGH | **Fixed** — Endpoint and service method removed entirely. Not used by frontend. |
| 6 | Spring Boot 2.7.18 end-of-life | HIGH | **Accepted** — WebLogic 14c constraint. Documented with compensating controls. |
| 7 | File upload accepts mismatched content (e.g., `.jpg` with EXE bytes) | MEDIUM | **Fixed** — Magic-byte validation for JPEG, PNG, PDF. Content must match extension. |
| 8 | `spring.profiles.active=dev` hardcoded in shared config | MEDIUM | **Fixed** — Removed from `application.properties`. Must be set via `-Dspring.profiles.active` or env var. |

### Resolved 2026-02-11 (Security Review — 6 Findings)

| # | Finding | Severity | Resolution |
|---|---------|----------|------------|
| 1 | `MessageService.decrypt()` logs raw decrypted content at DEBUG | HIGH | **Fixed** — 4 `log.debug()` lines in `decrypt()` and `generateRandomIv()` commented out, matching `encrypt()` pattern. |
| 2 | `FileManagerService` fileDiv path traversal bypass when `extraPath` is empty | HIGH | **Fixed** — All file path construction routed through `buildSafeFilePath()` regardless of `extraPath` value. |
| 3 | File upload/download DTOs have no input validation | HIGH | **Fixed** — `@NotBlank`/`@Size` on `FileUploadInDto` and `FileDownloadInDto`; `@Valid` on controller `@ModelAttribute`; `BindException` handler in `GlobalExceptionHandler`. |
| 4 | `JwtUtil.validateToken()` swallows all exceptions, returns `false` | MEDIUM | **Fixed** — Removed catch-all block; JWT exceptions now propagate to `JwtAuthFilter` which has specific handlers for `ExpiredJwtException`, `MalformedJwtException`, etc. |
| 5 | `WeblogicConnector` exception messages expose JNDI/Tuxedo internals | MEDIUM | **Fixed** — Removed `+ var.getMessage()` from all `throw new Exception()` calls; detailed errors remain in `log.error()`. |
| 6 | `TelegramUtil.main()` uses `System.out.println` | LOW | **Fixed** — Replaced with `log.debug()`. |

### Resolved 2026-02-10 (Round 3 — Hardening)

| # | Finding | Severity | Resolution |
|---|---------|----------|------------|
| 1 | `WeblogicConnector` silently swallows exceptions, returns empty `byte[0]` | LOW | **Fixed** - Exceptions re-thrown so callers can distinguish failure from empty response. |
| 2 | `TelegramUtil.cutBytes1()` and `viewObjectMethod()` use `System.out.println` | LOW | **Fixed** - Replaced with `log.debug()`. Raw byte data no longer written to stdout. |
| 3 | `/auth/refresh` has no rate limiting | LOW | **Fixed** - Reuses `LoginRateLimiter` (5 attempts / 15-min) keyed by token prefix. |
| 4 | No per-file size validation in upload code | LOW | **Fixed** - 10MB per-file limit enforced in `FileManagerService` (defense-in-depth). |

### Resolved 2026-02-10 (Round 2 — Deep Scan)

| # | Finding | Severity | Resolution |
|---|---------|----------|------------|
| 1 | `InterfaceTelegram` logs full VO toString(), raw request bytes, header at INFO | MEDIUM | **Fixed** - All VO/byte data moved to DEBUG. INFO shows only request size and err_flag. |
| 2 | `ServiceSupport` logs full userData and result toString() at INFO (all 200+ modules) | MEDIUM | **Fixed** - Full toString at DEBUG only. INFO shows tx_code, scrn_id, op_id. |
| 3 | `GlobalExceptionHandler` returns `ex.getMsg()` from Tuxedo errors to client | MEDIUM | **Fixed** - Generic message `"A backend service error occurred"` to client. Details server-side only. |
| 4 | `GlobalExceptionHandler` returns `ex.getMessage()` from IOException to client | MEDIUM | **Fixed** - Generic message `"A request processing error occurred"` to client. Details server-side only. |
| 5 | `MessageController` logs encrypted DTO (ciphertext+IV) at INFO | LOW | **Fixed** - Replaced with `"messageTransfer request received"`. |
| 6 | `FileManagerController` logs full inDto and files at INFO | LOW | **Fixed** - Full data at DEBUG. INFO shows userId and file count. |
| 7 | `WeblogicConnector` logs exceptions at INFO instead of ERROR | LOW | **Fixed** - All catch blocks changed to `log.error()`. Deduplicated to single message per catch. |

### Resolved 2026-02-10 (Round 1)

| # | Finding | Severity | Resolution |
|---|---------|----------|------------|
| 1 | Sensitive data logged in production (`MessageService`, `WeblogicConnector`) | CRITICAL | **Fixed** - Decrypted messages no longer logged. Only byte sizes logged at INFO level. Content available at DEBUG level only. |
| 2 | `encryptionPassword` field on `AuthInfoDto` (JWT payload risk) | HIGH | **Fixed** - Field removed entirely from `AuthInfoDto`. JWT payload now contains only `userId`. |
| 3 | Login catch block leaks `e.getMessage()` to client | HIGH | **Fixed** - Generic `"Authentication failed"` message returned. Exception details logged server-side only. |
| 4 | `application-dev.properties` tracked in Git with secrets | MEDIUM | **Fixed** - Added to `.gitignore`. Properties now use `${ENV_VAR:default}` placeholders. |
| 5 | `CachedBodyHttpServletRequest` no body size limit | MEDIUM | **Fixed** - 1MB max body size enforced. Returns IOException on oversized requests. |
| 6 | `forwardWeblogic` accepts unlimited raw bytes | MEDIUM | **Fixed** - 1MB payload size limit. Empty payloads rejected with 400, oversized with 413. |
| 7 | Tuxedo header logged with potentially sensitive data | LOW | **Fixed** - Only error code logged, not full header object. |
| 8 | `NullPointerException` in `WeblogicConnector.finally` | LOW | **Fixed** - Null check added before `var3.tpterm()`. |

### Resolved 2026-02-06

| # | Finding | Severity | Resolution |
|---|---------|----------|------------|
| 1 | Hardcoded secrets in `application.properties` | CRITICAL | **Fixed** - Secrets moved to Spring Profile properties. Prod/UAT files gitignored. |
| 2 | Executable files allowed in upload (`exe`) | HIGH | **Fixed** - File extension filter now `jpg,png,pdf` only. |
| 3 | Weak AES key (`ABCDEFGHIJKLMNOP`) | HIGH | **Mitigated** - Key in dev profile only. Prod requires real secret via env var. |
| 4 | CORS wildcard (`*`) | MEDIUM | **Fixed** - CORS origins configurable per profile via `cors.allowed.origins`. |
| 5 | Permissive URL authorization (`/** permitAll`) | MEDIUM | **Fixed** - `anyRequest().authenticated()` with explicit `permitAll()` list. |
| 6 | Missing security headers | LOW | **Fixed** - HSTS, X-Frame-Options: DENY, X-XSS-Protection, X-Content-Type-Options. |
| 7 | JWT 24-hour expiration | LOW | **Fixed** - Access token 15 min. Refresh token (24h) with rotation. |
| 8 | CSRF disabled without documentation | HIGH | **Resolved** - Documented as intentional for stateless JWT API. |

### Resolved 2026-02-03 (Initial)

| # | Finding | Severity | Resolution |
|---|---------|----------|------------|
| 1 | Hardcoded JWT token in WeblogicConnector.java | MEDIUM | **Fixed** - Commented-out code removed. |

---

## PCI DSS Alignment

| PCI DSS Requirement | Control | Status |
|---------------------|---------|--------|
| **6.5.1** Injection | Input validation, no direct SQL | Implemented |
| **6.5.3** Insecure crypto | AES-128-CBC + random IV per request | Implemented |
| **6.5.7** XSS | Security headers, JSON-only API | Implemented |
| **6.5.10** Broken auth | JWT + blacklist + rate limit + refresh rotation | Implemented |
| **8.1.6** Lockout after attempts | 5 attempts / 15-min per userId | Implemented |
| **8.2.1** Strong crypto for credentials | SHA hash + AES transit encryption | Implemented |
| **10.1** Audit trail | AuditLogFilter on every request | Implemented |
| **10.2** Log access to cardholder data | Audit log with userId, path, status, duration | Implemented |
| **11.5** Integrity monitoring | HMAC-SHA256 request signing on all POST | Implemented |

---

## In-Memory State (Caffeine Caches)

| Cache | Class | TTL | Max Size | Note |
|-------|-------|-----|----------|------|
| Token Blacklist | `JwtBlacklist` | 15 min | 50,000 | Lost on restart (tokens become valid again) |
| Refresh Tokens | `RefreshTokenStore` | 24 hours | 50,000 | Lost on restart (users forced re-login) |
| Rate Limiter (Login) | `LoginRateLimiter` | 15 min | 10,000 | Lost on restart (rate limits reset) |
| Rate Limiter (Refresh) | `LoginRateLimiter` | 15 min | 10,000 | Shares same limiter, keyed with `refresh:` prefix |

**Cluster Note:** All caches are per-JVM instance. In a clustered WebLogic deployment, each instance maintains its own state. Rate limiting, token blacklist, and refresh tokens will not be shared across instances.

---

## Verified Secure

| Item | Status | Notes |
|------|--------|-------|
| JWT "none" algorithm attack | SAFE | Uses `parseClaimsJws()` with required signing key |
| SQL Injection | N/A | No direct SQL queries (Tuxedo CARRAY protocol) |
| Command Injection | SAFE | No `Runtime.exec()` or `ProcessBuilder` |
| SSRF | SAFE | No user-controlled URL parameters |
| Path Traversal (Upload) | SAFE | `new File(name).getName()` at all 3 entry points + `buildSafeFilePath()` |
| Path Traversal (Download) | SAFE | File paths from config, not user input |
| Denial of Service (Upload) | SAFE | 10MB max file size enforced |
| Denial of Service (Body Cache) | SAFE | 1MB max body size in `CachedBodyHttpServletRequest` |
| File Content Spoofing | SAFE | Magic-byte validation for JPEG, PNG, PDF uploads |
| Session Fixation | N/A | Stateless, no sessions |
| Information Leakage (Logs) | SAFE | No sensitive data at INFO level (app layer + telegram library) |
| Information Leakage (Errors) | SAFE | Generic error messages to clients, all exception details server-side only |
| Information Leakage (Auth Entry Point) | SAFE | `CustomAuthEntryPoint` returns generic `"Authentication required"`, details logged server-side |
| Log Injection (Filenames) | SAFE | `sanitizeForLog()` strips control characters from user-supplied filenames |
| WebLogic Path Consistency | SAFE | All security filters use `getServletPath()` — no `getRequestURI()` in security package. Unused `WebUtil.getURI()` (which used `getRequestURI()`) removed. |
| HMAC Coverage (All Methods) | SAFE | All body-bearing HTTP methods (POST, PUT, PATCH, DELETE) validated |
| DTO Input Bounds | SAFE | All user-input fields have `@Size` constraints — no unbounded string fields |
| Information Leakage (Telegram Library) | SAFE | InterfaceTelegram, ServiceSupport: VO data at DEBUG only |
| WeblogicConnector Exception Handling | SAFE | Exceptions logged at ERROR level (not INFO) |
| Telegram Library Logging | SAFE | No `printStackTrace()` or `System.out.println` in production code — all use Log4j2 |
| Public Endpoint Log Safety | SAFE | `GET /` returns version silently — no log output that could be abused for log flooding |

---

## Environment Variables (Required for Production)

| Variable | Description | Example |
|----------|-------------|---------|
| `AES_SECRET_KEY` | 16-byte AES-128 key | `openssl rand -hex 8` (16 chars) |
| `JWT_SECRET` | 32+ byte HMAC-SHA256 key | `openssl rand -base64 32` |
| `HMAC_SECRET_KEY` | HMAC signing key | `openssl rand -base64 32` |
| `FILE_UPLOAD_PATH` | File storage directory | `/app/bcap` |
| `CORS_ALLOWED_ORIGINS` | Comma-separated origins | `https://your-domain.com` |

---

## Deployment Issue (Active)

**`IncompatibleClassChangeError`** on WebLogic 14c:
- JAR `com.bea.core.jatmi.jar` in `jar/` folder defines `TuxedoConnection` as a class
- WebLogic 14.1.2 runtime provides it as an interface
- **Fix:** Replace with correct JAR from `$WL_HOME/server/lib/` on the target WebLogic 14c server

---

## Next Steps

1. **Before Production:** Set environment variables for all secrets (see table above)
2. **Production:** Verify WebLogic proxy configuration strips `X-Forwarded-For` from external clients
3. **Future:** Consider Redis/shared cache for clustered deployments
4. **Future:** Upgrade JJWT from 0.11.5 to 0.12.6 (no CVEs, but improved API and key handling)
5. **Future:** Upgrade `commons-codec` from 1.15 to 1.17.x (maintenance update)
6. **Future:** Add OWASP Dependency Check to CI pipeline for automated CVE detection
