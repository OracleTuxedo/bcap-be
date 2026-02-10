# Security Vulnerability Assessment Report
## Project: bcap-be (Spring Boot Backend)

**Date:** 2026-02-10 (Updated)
**Previous Assessment:** 2026-02-06
**Assessed By:** Claude Code Security Analysis
**Framework:** Spring Boot 2.7.18 / Java 21
**Version:** 2.5.1

---

## Executive Summary

| Severity | 2026-02-03 | 2026-02-06 | 2026-02-10 (R1) | 2026-02-10 (R2) | Trend |
|----------|------------|------------|-----------------|-----------------|-------|
| **CRITICAL** | 2 | 1 | 0 | 0 | All resolved |
| **HIGH** | 3 | 1 | 0 | 0 | All resolved |
| **MEDIUM** | 3 | 2 | 1 | 1 | 1 accepted |
| **LOW** | 2 | 0 | 0 | 0 | All resolved |
| **Total Open** | **10** | **4** | **1** | **1** | **7 fixed in deep scan** |

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

## Implemented Security Controls (21 Total)

| # | Control | Component | Details |
|---|---------|-----------|---------|
| 1 | JWT Authentication | `JwtUtil.java` | HS256, 15-min expiry |
| 2 | Token Blacklist | `JwtBlacklist.java` | Caffeine, 15-min TTL, 50K max |
| 3 | Refresh Token Rotation | `RefreshTokenStore.java` | UUID, 24h expiry, rotate on use |
| 4 | Login Rate Limiting | `LoginRateLimiter.java` | 5 attempts / 15-min per userId |
| 5 | HMAC Request Signing | `HmacVerificationFilter.java` | HmacSHA256 on all POST bodies |
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
| 18 | Payload Size Validation | `MessageController.java` | `/forward/weblogic` limited to 1MB |
| 19 | Telegram Library Log Sanitization | `InterfaceTelegram.java` | VO/byte data at DEBUG only, INFO shows sizes and err_flag |
| 20 | ServiceSupport Log Sanitization | `ServiceSupport.java` | Full toString at DEBUG only, INFO shows tx_code/scrn_id/op_id |
| 21 | Error Response Sanitization | `GlobalExceptionHandler.java` | Generic messages to client, details server-side only |

---

## Access Control Matrix

| Path | Auth | HMAC | Rate Limited | Payload Limit |
|------|:----:|:----:|:------------:|:-------------:|
| `GET /` | No | No (GET) | No | N/A |
| `GET /actuator/health` | No | No (GET) | No | N/A |
| `POST /auth/login` | No | Yes | Yes (5/15min) | 1MB |
| `POST /auth/refresh` | No | Yes | No | 1MB |
| `POST /auth/logout` | Yes | Yes | No | 1MB |
| `POST /message` | Yes | Yes | No | 1MB |
| `POST /message/forward/weblogic` | Yes | Yes | No | 1MB |
| `POST /file-manager/upload` | Yes | Yes | No | 10MB (multipart) |
| `GET /file-manager/download` | Yes | No (GET) | No | N/A |

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

---

## Resolved Findings

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
| Rate Limiter | `LoginRateLimiter` | 15 min | 10,000 | Lost on restart (rate limits reset) |

**Cluster Note:** All caches are per-JVM instance. In a clustered WebLogic deployment, each instance maintains its own state. Rate limiting, token blacklist, and refresh tokens will not be shared across instances.

---

## Verified Secure

| Item | Status | Notes |
|------|--------|-------|
| JWT "none" algorithm attack | SAFE | Uses `parseClaimsJws()` with required signing key |
| SQL Injection | N/A | No direct SQL queries (Tuxedo CARRAY protocol) |
| Command Injection | SAFE | No `Runtime.exec()` or `ProcessBuilder` |
| SSRF | SAFE | No user-controlled URL parameters |
| Path Traversal (Upload) | SAFE | `new File(name).getName()` + `buildSafeFilePath()` |
| Path Traversal (Download) | SAFE | File paths from config, not user input |
| Denial of Service (Upload) | SAFE | 10MB max file size enforced |
| Denial of Service (Body Cache) | SAFE | 1MB max body size in `CachedBodyHttpServletRequest` |
| Denial of Service (Forward) | SAFE | 1MB max payload in `/forward/weblogic` |
| Session Fixation | N/A | Stateless, no sessions |
| Information Leakage (Logs) | SAFE | No sensitive data at INFO level (app layer + telegram library) |
| Information Leakage (Errors) | SAFE | Generic error messages to clients, all exception details server-side only |
| Information Leakage (Telegram Library) | SAFE | InterfaceTelegram, ServiceSupport: VO data at DEBUG only |
| WeblogicConnector Exception Handling | SAFE | Exceptions logged at ERROR level (not INFO) |

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
