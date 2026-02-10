# BCAP-BE Security Features Implementation Plan

**Date:** 2026-02-06
**Project:** bcap-be (Spring Boot 2.7.18)
**Scope:** Security hardening within current architecture constraints

---

## Project Constraints

Before planning, these are the hard limits we work within:

| Constraint | Detail |
|------------|--------|
| No database | All persistence goes through Tuxedo backend |
| No Redis | WebLogic 12c environment, no external cache infra |
| Spring Boot 2.7.x | Must stay on javax namespace (not Jakarta) |
| Stateless | JWT-based, no server-side sessions |
| In-memory only | Caffeine cache is the only local state available |
| Single WAR | Deployed on WebLogic 12c, no microservices |

---

## Feature Overview

| # | Feature | Priority | Effort | Dependencies |
|---|---------|----------|--------|--------------|
| 1 | Externalize Secrets | CRITICAL | Low | Environment variables |
| 2 | Sanitize Log Output | CRITICAL | Low | None |
| 3 | Global Exception Handler | HIGH | Low | None |
| 4 | Security Response Headers | HIGH | Low | None |
| 5 | Rate Limiting (Login) | HIGH | Medium | Caffeine cache |
| 6 | Refresh Token Mechanism | HIGH | Medium | Caffeine cache |
| 7 | Deny-by-Default Authorization | HIGH | Low | None |
| 8 | Production CORS Configuration | MEDIUM | Low | Environment variables |
| 9 | Request/Response Audit Logging | MEDIUM | Medium | None |
| 10 | File Upload Hardening | MEDIUM | Low | None |
| 11 | JWT Blacklist (Logout) | MEDIUM | Medium | Caffeine cache |
| 12 | Input Validation Framework | LOW | Medium | None |

---

## Feature Details

---

### 1. Externalize Secrets
**Priority:** CRITICAL | **Effort:** Low | **Risk if skipped:** Credential compromise

**Problem:**
```properties
# application.properties - secrets in source control
aes.secret.key=ABCDEFGHIJKLMNOP
jwt_secret=3ZxNqN1V+7Tg4X5FZ9fD3+P0tU61KcLdNq8p0GZbG2w=
```

**Solution:**
```properties
# application.properties - reference env vars
aes.secret.key=${AES_SECRET_KEY}
jwt_secret=${JWT_SECRET}
```

**What to implement:**
- Replace hardcoded values with `${ENV_VAR}` placeholders
- Add `application-local.properties` to `.gitignore` for local dev
- Generate strong keys: `openssl rand -base64 32` for both
- Document required env vars in README

**Files to change:**
- `src/main/resources/application.properties`
- `.gitignore` (add `application-local.properties`)

---

### 2. Sanitize Log Output
**Priority:** CRITICAL | **Effort:** Low | **Risk if skipped:** Token/password leakage via logs

**Problem:**
```java
// AuthService.java:84-87 - passwords and tokens logged
log.info("AuthInfoDto [{}]", authInfoDto.toString());  // contains encryptionPassword
log.info("token [{}]", token);                          // full JWT
```

**Solution:**
```java
// Option A: Remove sensitive logs entirely
log.info("Login successful for user [{}]", inDto.getUserId());

// Option B: Mask sensitive fields in toString()
// AuthInfoDto.java
@Override
public String toString() {
    return "AuthInfoDto{userId='" + userId + "', encryptionPassword='***'}";
}
```

**What to implement:**
- Audit all `log.info` calls that output DTOs containing passwords/tokens
- Override `toString()` on `AuthInfoDto` to mask `encryptionPassword`
- Remove `log.info("token [{}]", token)` from AuthService
- Remove `log.info` of raw Tuxedo request/response bytes in MessageService (contains user data)

**Files to change:**
- `AuthService.java` (lines 53, 65, 84, 87)
- `MessageService.java` (lines 43, 49, 50)
- `AuthInfoDto.java` (add masked `toString()`)
- `AuthController.java` (lines 40, 48, 50)

---

### 3. Global Exception Handler
**Priority:** HIGH | **Effort:** Low | **Risk if skipped:** Stack trace leakage to clients

**Problem:**
No `@ControllerAdvice` exists. Unhandled exceptions return raw stack traces to the client, exposing internal class names, paths, and library versions.

**Solution:** Create a `GlobalExceptionHandler`.

```java
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneral(Exception ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", 500);
        body.put("error", "Internal Server Error");
        body.put("message", "An unexpected error occurred");
        // Do NOT include ex.getMessage() - may contain sensitive info
        return ResponseEntity.status(500).body(body);
    }

    @ExceptionHandler(TelegramNestedRuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleTelegram(TelegramNestedRuntimeException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", 502);
        body.put("error", "Backend Service Error");
        body.put("message", ex.getMsg());
        return ResponseEntity.status(502).body(body);
    }
}
```

**What to implement:**
- New file: `controller/GlobalExceptionHandler.java`
- Handle `TelegramNestedRuntimeException`, `IOException`, `JwtException`, generic `Exception`
- Return consistent JSON error format, never expose stack traces

**Files to create:**
- `src/main/java/maas/bcap/controller/GlobalExceptionHandler.java`

---

### 4. Security Response Headers
**Priority:** HIGH | **Effort:** Low | **Risk if skipped:** XSS, clickjacking, MIME sniffing

**Problem:**
No security headers are set on responses.

**Solution:** Add to `SecurityConfig.java`:

```java
.headers(headers -> headers
    .contentTypeOptions(Customizer.withDefaults())     // X-Content-Type-Options: nosniff
    .frameOptions(frame -> frame.deny())               // X-Frame-Options: DENY
    .httpStrictTransportSecurity(hsts -> hsts
        .includeSubDomains(true)
        .maxAgeInSeconds(31536000))                    // HSTS: 1 year
    .xssProtection(xss -> xss.headerValue(            // X-XSS-Protection
        XXssProtectionHeaderWriter.HeaderValue.ENABLED_MODE_BLOCK))
)
```

**What to implement:**
- Add `.headers()` configuration to `SecurityConfig.filterChain()`
- Spring Security 5.x (in Boot 2.7) has built-in support for all these

**Files to change:**
- `SecurityConfig.java`

---

### 5. Rate Limiting (Login)
**Priority:** HIGH | **Effort:** Medium | **Risk if skipped:** Brute force attacks

**Problem:**
No rate limiting on `/auth/login`. Attackers can attempt unlimited password guesses.

**Solution:** Use Caffeine cache as in-memory rate limiter.

```java
@Component
public class LoginRateLimiter {
    // Key: IP or userId, Value: attempt count
    private final Cache<String, Integer> attempts = Caffeine.newBuilder()
        .expireAfterWrite(15, TimeUnit.MINUTES)
        .maximumSize(10_000)
        .build();

    private static final int MAX_ATTEMPTS = 5;

    public boolean isBlocked(String key) {
        Integer count = attempts.getIfPresent(key);
        return count != null && count >= MAX_ATTEMPTS;
    }

    public void recordFailedAttempt(String key) {
        attempts.asMap().merge(key, 1, Integer::sum);
    }

    public void reset(String key) {
        attempts.invalidate(key);
    }
}
```

**What to implement:**
- New file: `security/LoginRateLimiter.java`
- Integrate into `AuthService.login()` - check before calling Tuxedo
- Return 429 Too Many Requests when blocked
- Reset counter on successful login
- Key by userId (not IP, to avoid proxy issues)

**Limitation:** In-memory only (Caffeine). Resets on restart. Acceptable for single-instance WebLogic deploy.

**Files to create:**
- `src/main/java/maas/bcap/security/LoginRateLimiter.java`

**Files to change:**
- `AuthService.java`

---

### 6. Refresh Token Mechanism
**Priority:** HIGH | **Effort:** Medium | **Risk if skipped:** Long-lived tokens if stolen

**Problem:**
Single JWT with 24-hour expiration. No way to rotate tokens without re-login.

**Solution:** Short-lived access token + longer-lived refresh token stored in Caffeine.

```
Access Token:  15 minutes (JWT, sent in Authorization header)
Refresh Token: 24 hours   (random UUID, stored in Caffeine cache)
```

**Flow:**
```
1. POST /auth/login
   -> Returns: { accessToken (15min), refreshToken (UUID) }
   -> Server stores: Caffeine[refreshToken -> userId] (24h TTL)

2. POST /auth/refresh  (new endpoint)
   -> Client sends: { refreshToken }
   -> Server validates: Caffeine lookup
   -> Returns: { new accessToken (15min), new refreshToken }
   -> Old refresh token invalidated (rotation)

3. POST /auth/logout
   -> Server removes: Caffeine[refreshToken]
```

**What to implement:**
- New file: `security/RefreshTokenStore.java` (Caffeine-backed)
- New DTO: `RefreshTokenInDto.java`, `RefreshTokenOutDto.java`
- New endpoint: `POST /auth/refresh` in AuthController
- Change `JwtUtil.expiration` from 24h to 15min
- Update login to return both tokens
- Update logout to invalidate refresh token

**Limitation:** Caffeine is in-memory. Refresh tokens lost on restart (users must re-login). Acceptable trade-off.

**Files to create:**
- `src/main/java/maas/bcap/security/RefreshTokenStore.java`
- `src/main/java/maas/bcap/dto/RefreshTokenInDto.java`
- `src/main/java/maas/bcap/dto/RefreshTokenOutDto.java`

**Files to change:**
- `JwtUtil.java` (reduce expiration)
- `AuthController.java` (add /refresh endpoint)
- `AuthService.java` (generate and return refresh token)
- `LoginOutDto.java` (add refreshToken field)

---

### 7. Deny-by-Default Authorization
**Priority:** HIGH | **Effort:** Low | **Risk if skipped:** Accidental endpoint exposure

**Problem:**
```java
// SecurityConfig.java:37 - everything is open by default
.antMatchers("/**").permitAll()
```
Any new endpoint added by a developer is automatically public.

**Solution:**
```java
.authorizeHttpRequests(auth -> auth
    .antMatchers("/auth/login", "/auth/refresh").permitAll()
    .antMatchers("/actuator/health").permitAll()
    .antMatchers("/").permitAll()
    .anyRequest().authenticated()  // DENY by default
)
```

**What to implement:**
- Replace `permitAll("/**")` with `anyRequest().authenticated()`
- Explicitly list public endpoints

**Files to change:**
- `SecurityConfig.java`

---

### 8. Production CORS Configuration
**Priority:** MEDIUM | **Effort:** Low | **Risk if skipped:** Cross-origin abuse

**Problem:**
```java
cfg.setAllowedOrigins(List.of("*"));  // any website can call API
```

**Solution:**
```properties
# application.properties
cors.allowed.origins=${CORS_ORIGINS:http://localhost:3000}
```

```java
@Value("${cors.allowed.origins}")
private String allowedOrigins;

cfg.setAllowedOrigins(Arrays.asList(allowedOrigins.split(",")));
```

**What to implement:**
- Add `cors.allowed.origins` property (env-configurable)
- Inject into SecurityConfig
- Default to `localhost:3000` for development

**Files to change:**
- `application.properties`
- `SecurityConfig.java`

---

### 9. Request/Response Audit Logging
**Priority:** MEDIUM | **Effort:** Medium | **Risk if skipped:** No audit trail

**Problem:**
No structured audit logging. Can't trace who called what and when.

**Solution:** Create an audit logging filter.

```java
@Component
public class AuditLogFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(...) {
        long start = System.currentTimeMillis();
        filterChain.doFilter(request, response);
        long duration = System.currentTimeMillis() - start;

        // Log: timestamp, userId, method, path, status, duration
        log.info("AUDIT | user={} | {}:{} | status={} | {}ms",
            getUserId(request), request.getMethod(), request.getRequestURI(),
            response.getStatus(), duration);
    }
}
```

**What to implement:**
- New file: `security/AuditLogFilter.java`
- Register in SecurityConfig (after JwtAuthFilter)
- Log: userId, HTTP method, path, response status, duration
- Exclude sensitive request bodies
- Use dedicated log appender (`audit.log`)

**Files to create:**
- `src/main/java/maas/bcap/security/AuditLogFilter.java`

**Files to change:**
- `SecurityConfig.java` (register filter)
- `log4j2-spring.xml` (add audit appender)

---

### 10. File Upload Hardening
**Priority:** MEDIUM | **Effort:** Low | **Risk if skipped:** Malware upload, path traversal

**Problem:**
```properties
file.ext.filter=jpg,png,pdf,exe    # exe is allowed!
```
Extension check is suffix-based only (no magic byte validation).

**Solution:**

```java
// 1. Remove exe from allowed list
file.ext.filter=jpg,png,pdf

// 2. Add content-type validation (magic bytes)
private boolean validateFileContent(MultipartFile file) throws IOException {
    String contentType = file.getContentType();
    byte[] header = new byte[4];
    file.getInputStream().read(header);

    // Check magic bytes match declared content type
    if (contentType.contains("image/jpeg") && header[0] == (byte)0xFF && header[1] == (byte)0xD8)
        return true;
    if (contentType.contains("image/png") && header[0] == (byte)0x89 && header[1] == 0x50)
        return true;
    if (contentType.contains("pdf") && header[0] == 0x25 && header[1] == 0x50)
        return true;
    return false;
}

// 3. Sanitize filename (prevent path traversal)
String safeName = Paths.get(originalFileName).getFileName().toString();
safeName = safeName.replaceAll("[^a-zA-Z0-9._-]", "_");
```

**What to implement:**
- Remove `exe` from `file.ext.filter`
- Add magic byte validation in `FileManagerService.checkFileExtension()`
- Sanitize uploaded filenames (strip path components, special chars)

**Files to change:**
- `application.properties`
- `FileManagerService.java`

---

### 11. JWT Blacklist (Logout)
**Priority:** MEDIUM | **Effort:** Medium | **Risk if skipped:** Tokens valid after logout

**Problem:**
Logout is a no-op. JWT tokens remain valid for 24 hours (or 15min after Feature #6) after "logout".

**Solution:** Caffeine-backed token blacklist.

```java
@Component
public class JwtBlacklist {
    private final Cache<String, Boolean> blacklist = Caffeine.newBuilder()
        .expireAfterWrite(15, TimeUnit.MINUTES)  // match access token TTL
        .maximumSize(50_000)
        .build();

    public void blacklist(String token) {
        blacklist.put(token, true);
    }

    public boolean isBlacklisted(String token) {
        return blacklist.getIfPresent(token) != null;
    }
}
```

**What to implement:**
- New file: `security/JwtBlacklist.java`
- On logout: add current access token to blacklist
- In `JwtAuthFilter`: check blacklist before accepting token
- TTL matches access token expiration (no stale entries)

**Dependency:** Best combined with Feature #6 (short-lived tokens reduce blacklist size)

**Files to create:**
- `src/main/java/maas/bcap/security/JwtBlacklist.java`

**Files to change:**
- `JwtAuthFilter.java` (check blacklist)
- `AuthService.java` (blacklist on logout)
- `AuthController.java` (pass token to logout)

---

### 12. Input Validation Framework
**Priority:** LOW | **Effort:** Medium | **Risk if skipped:** Bad data reaching Tuxedo

**Problem:**
No input validation on DTOs. Raw user input goes through encryption and into Tuxedo.

**Solution:** Add Bean Validation (already available in Spring Boot).

```java
// LoginInDto.java
public class LoginInDto {
    @NotBlank(message = "userId is required")
    @Size(max = 20)
    private String userId;

    @NotBlank(message = "password is required")
    private String encryptionPassword;

    @NotBlank
    @Pattern(regexp = "^[A-Z]$")  // single char like "O", "M"
    private String appType;
}

// Controller
public ResponseEntity<LoginOutDto> login(
    HttpServletRequest request,
    @Valid @RequestBody LoginInDto inDto) { ... }
```

**What to implement:**
- Add `spring-boot-starter-validation` to pom.xml (may already be transitive)
- Add `@NotBlank`, `@Size`, `@Pattern` annotations to key DTOs
- Add `@Valid` to controller method parameters
- Handle `MethodArgumentNotValidException` in GlobalExceptionHandler

**Files to change:**
- `pom.xml` (if validation starter missing)
- `LoginInDto.java`, `MessageTransferInDto.java`, `FileUploadInDto.java`
- `AuthController.java`, `MessageController.java`, `FileManagerController.java`
- `GlobalExceptionHandler.java` (add validation error handler)

---

## Implementation Order (Recommended)

```
Phase 1 - Quick Wins (1-2 days)
├── 1. Externalize Secrets         ← CRITICAL, 30 min
├── 2. Sanitize Log Output         ← CRITICAL, 1 hour
├── 4. Security Response Headers   ← HIGH, 30 min
├── 7. Deny-by-Default Auth        ← HIGH, 15 min
└── 10. File Upload Hardening      ← MEDIUM, 1 hour

Phase 2 - Core Security (2-3 days)
├── 3. Global Exception Handler    ← HIGH, 1 hour
├── 5. Rate Limiting (Login)       ← HIGH, 2 hours
├── 8. Production CORS Config      ← MEDIUM, 30 min
└── 9. Audit Logging               ← MEDIUM, 3 hours

Phase 3 - Token Security (2-3 days)
├── 6. Refresh Token Mechanism     ← HIGH, 4 hours
├── 11. JWT Blacklist (Logout)     ← MEDIUM, 2 hours
└── 12. Input Validation           ← LOW, 3 hours
```

---

## Architecture After Implementation

```
                        Request Flow
                        ============

Client Request
     |
     v
[Security Headers]          ← Feature #4
     |
     v
[CORS Check]                ← Feature #8
     |
     v
[Rate Limiter]              ← Feature #5 (login only)
     |
     v
[JWT Auth Filter]           ← Existing + Feature #11 (blacklist check)
     |
     v
[Audit Log Filter]          ← Feature #9
     |
     v
[Input Validation]          ← Feature #12
     |
     v
[Controller]
     |
     v
[Service Layer]
     |
     v
[Tuxedo Gateway]

                    Token Lifecycle
                    ===============

Login ─────> Access Token (15min) + Refresh Token (24h, Caffeine)
                  |                        |
                  |                        v
                  |              POST /auth/refresh
                  |                        |
                  v                        v
              Expires              New Access Token + New Refresh Token
                  |                        |
                  v                        v
             Re-authenticate         Old Refresh Invalidated

Logout ────> Access Token Blacklisted (Caffeine, 15min TTL)
             Refresh Token Removed from Caffeine
```

---

## Summary

| Phase | Features | Fixes |
|-------|----------|-------|
| Phase 1 | #1, #2, #4, #7, #10 | All CRITICAL + easy HIGHs |
| Phase 2 | #3, #5, #8, #9 | Remaining HIGHs + MEDIUMs |
| Phase 3 | #6, #11, #12 | Token hardening + validation |

**Total new files:** 6
**Total modified files:** ~15
**No new dependencies required** (Caffeine already included, validation may be transitive)
