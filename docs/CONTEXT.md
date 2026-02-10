# BCAP Backend - Developer Context Guide
## Version 2.5.1 | Spring Boot 2.7.18 | Java 21

---

## CHANGELOG (Frontend Breaking Changes)

### 2026-02-10 (Update 4) — Deep Scan Security Fixes

| Change | Type | Frontend Impact |
|--------|------|-----------------|
| Tuxedo/IO error responses are now generic | **INFO** | `502` errors return `"A backend service error occurred"` instead of internal Tuxedo error messages. `400` IO errors return `"A request processing error occurred"`. |
| Telegram library logging reduced to DEBUG | **INFO** | No frontend impact. `InterfaceTelegram` and `ServiceSupport` no longer log full VO/byte data at INFO level. |
| WeblogicConnector exceptions now logged at ERROR | **INFO** | No frontend impact. Tuxedo connection failures properly logged as errors. |
| Controller verbose logging reduced | **INFO** | No frontend impact. Encrypted DTO and file metadata no longer logged at INFO. |

### 2026-02-10 (Update 3) — Security Remediation

| Change | Type | Frontend Impact |
|--------|------|-----------------|
| `encryptionPassword` field removed from `AuthInfoDto` | **BREAKING** | JWT payload now only contains `userId`. The `encryptionPassword` getter/setter no longer exists on `AuthInfoDto`. |
| Login error messages are now generic | **INFO** | Failed login now returns `"Authentication failed"` instead of internal error details. No frontend change needed. |
| `POST /message/forward/weblogic` now has 1MB payload limit | **INFO** | Payloads over 1MB will return `413`. Empty payloads return `400`. |
| Secrets externalized to environment variables | **INFO** | `application-dev.properties` now uses `${ENV_VAR:default}` format. Set env vars or use defaults for local dev. |
| Backend logs no longer contain decrypted messages | **INFO** | No frontend impact. Tuxedo request/response content no longer logged. |
| Request body caching limited to 1MB | **INFO** | POST requests over 1MB (excluding multipart uploads) will be rejected. |

### 2026-02-10 (Update 2) — Input Validation & Cleanup

| Change | Type | Frontend Impact |
|--------|------|-----------------|
| `POST /auth/refresh` now validates `refreshToken` | **BREAKING** | Sending empty or null `refreshToken` will now return `400` with message `"Refresh token is required"` |
| `POST /auth/logout` now validates `userId` and `screenId` | **BREAKING** | Sending empty or null `userId`/`screenId` will now return `400` with validation error messages |
| `POST /message` now validates `encryptedMessage` and `iv` | **BREAKING** | Sending empty or null fields will now return `400` with validation error messages |
| Path traversal protection on file upload `extraPath` | **INFO** | `extraPath` containing `../` sequences will be rejected with `400`. Normal paths still work. |
| Dead code and unused imports removed | **INFO** | No frontend impact. Codebase cleanup only. |

### 2026-02-10 (Update 1) — Security Hardening

| Change | Type | Frontend Impact |
|--------|------|-----------------|
| `/auth/me1`, `/auth/me2`, `/auth/me3`, `/auth/me4` removed | **BREAKING** | Use `GET /auth/me` instead (same response, requires `Authorization: Bearer` header) |
| `encryptionPassword` removed from JWT payload | **BREAKING** | If you decoded the JWT to read `encryptionPassword`, it is no longer there. Only `userId` remains in the payload. |
| `/example/*` endpoints disabled in production | **INFO** | These endpoints (`/example/decrypt`, `/example/process`, `/example/login`, etc.) only work in dev environment now. |
| CORS updated | **INFO** | Added `192.168.79.36`, `192.168.79.38`, `108.137.141.243` to allowed origins. |
| Sensitive data removed from backend logs | **INFO** | No frontend impact. Backend logs no longer contain passwords or JWT tokens. |
| HMAC comparison upgraded to constant-time | **INFO** | No frontend impact. Same `X-HMAC-Signature` header, same algorithm. |

### Migration Checklist for Frontend

- [ ] Replace all calls to `/auth/me1`, `/auth/me2`, `/auth/me3`, `/auth/me4` with `GET /auth/me`
- [ ] Stop decoding JWT to read `encryptionPassword` (if applicable)
- [ ] Ensure all POST requests send `X-HMAC-Signature` header (unchanged from before)
- [ ] Ensure `POST /auth/refresh` always sends non-empty `refreshToken`
- [ ] Ensure `POST /auth/logout` always sends non-empty `userId` and `screenId`
- [ ] Ensure `POST /message` always sends non-empty `encryptedMessage` and `iv`
- [ ] Handle `400` validation error responses (new format below)

#### New 400 Validation Error Format
```json
{
  "status": 400,
  "error": "Validation Error",
  "messages": [
    "refreshToken: Refresh token is required"
  ]
}
```

---

## 1. What Is This Project?

BCAP-BE is a **Spring Boot REST API gateway** that sits between a **Next.js frontend** and an **Oracle Tuxedo backend** (legacy mainframe-style system). It handles:

- **Authentication** via JWT access token + refresh token
- **Message relay** between frontend and Tuxedo (200+ backend services)
- **AES encryption** of messages in transit
- **File upload/download** management

```
Next.js Frontend
      |
      | HTTPS (AES-encrypted JSON)
      v
 BCAP-BE (this project)     <-- Spring Boot WAR on WebLogic 14c
      |
      | CARRAY (raw bytes via Tuxedo WTC)
      v
 Oracle Tuxedo (SLCFPROXY)
      |
      v
 200+ Backend Services (DevonC framework)
```

---

## 2. Frontend Integration Guide

### 2.1 Authentication Flow

#### Login
```
POST /auth/login
Content-Type: application/json

{
  "userId": "string",           // max 20 chars, required
  "encryptionPassword": "string", // AES-encrypted password, required
  "appType": "string",          // "I" = MTI, "B" = MBCS, "M" = MMP (1 char, required)
  "screenId": "string"          // required
}
```

**Success Response (200):**
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "refreshToken": "550e8400-e29b-41d4-a716-446655440000",
  "message": "OK"
}
```

**Failed Response (401):**
```json
{
  "token": null,
  "refreshToken": null,
  "message": "error description"
}
```

**Rate Limited Response (429):**
```json
{
  "token": null,
  "refreshToken": null,
  "message": "Too many login attempts. Please try again later."
}
```
Rate limit: **5 failed attempts per userId**, resets after **15 minutes**.

#### Token Storage (Frontend)
```
Access Token  -> store in memory (or short-lived storage)
Refresh Token -> store in memory (or short-lived storage)
```
- Access token expires in **15 minutes**
- Refresh token expires in **24 hours**

#### Refresh Token
When the access token expires (you get a `401`), call refresh:

```
POST /auth/refresh
Content-Type: application/json

{
  "refreshToken": "550e8400-e29b-41d4-a716-446655440000"
}
```

**Success Response (200):**
```json
{
  "accessToken": "eyJhbGciOiJIUzI1NiJ9...",
  "refreshToken": "new-uuid-refresh-token",
  "message": "OK"
}
```
**Important:** The refresh token **rotates** on every call. The old refresh token is invalidated immediately. Always store the new one.

**Failed Response (401):**
```json
{
  "accessToken": null,
  "refreshToken": null,
  "message": "Invalid or expired refresh token"
}
```
If refresh fails, redirect user to login page.

#### Logout
```
POST /auth/logout
Authorization: Bearer <access_token>
Content-Type: application/json

{
  "userId": "string",
  "screenId": "string",
  "refreshToken": "550e8400-e29b-41d4-a716-446655440000"
}
```
This blacklists the access token and invalidates the refresh token.

#### Using the Token
All protected endpoints require the `Authorization` header:
```
Authorization: Bearer <access_token>
```

### 2.2 Message Transfer (Main Business Flow)

All business operations go through a single encrypted endpoint:

```
POST /message
Authorization: Bearer <access_token>
Content-Type: application/json

{
  "encryptedMessage": "Base64-encoded-AES-ciphertext",
  "iv": "Base64-encoded-16-byte-IV"
}
```

**Response (200):**
```json
{
  "encryptedMessage": "Base64-encoded-AES-ciphertext",
  "iv": "Base64-encoded-16-byte-IV"
}
```

#### AES Encryption Spec

| Parameter | Value |
|-----------|-------|
| Algorithm | `AES/CBC/PKCS5Padding` |
| Key Size | 128-bit (16 bytes) |
| Key | Shared secret (same key on frontend and backend) |
| IV | Random 16 bytes, generated per request |
| Encoding | Base64 for both `encryptedMessage` and `iv` |

**Frontend pseudocode (encrypt):**
```javascript
// 1. Generate random 16-byte IV
const iv = crypto.getRandomValues(new Uint8Array(16));

// 2. Encrypt message with AES-CBC
const key = await crypto.subtle.importKey("raw", secretKeyBytes, "AES-CBC", false, ["encrypt"]);
const encrypted = await crypto.subtle.encrypt({ name: "AES-CBC", iv }, key, messageBytes);

// 3. Base64 encode both
const body = {
  encryptedMessage: btoa(String.fromCharCode(...new Uint8Array(encrypted))),
  iv: btoa(String.fromCharCode(...iv))
};
```

**Frontend pseudocode (decrypt response):**
```javascript
// 1. Base64 decode iv and encryptedMessage from response
const ivBytes = Uint8Array.from(atob(response.iv), c => c.charCodeAt(0));
const cipherBytes = Uint8Array.from(atob(response.encryptedMessage), c => c.charCodeAt(0));

// 2. Decrypt with AES-CBC using the SAME shared key
const decrypted = await crypto.subtle.decrypt({ name: "AES-CBC", iv: ivBytes }, key, cipherBytes);
const plaintext = new TextDecoder().decode(decrypted);
```

### 2.3 File Upload

```
POST /file-manager/upload
Authorization: Bearer <access_token>
Content-Type: multipart/form-data

Fields:
  - files: File[]              (multipart files, max 10MB each)
  - fileDiv: string            (file division/category)
  - fileDesc: string           (description)
  - extraPath: string          (sub-directory)
  - attachFileId: string       (attachment group ID)
  - attachFileSeqNo: string[]  (sequence numbers)
  - screenId: string
```

**Allowed extensions:** `jpg`, `png`, `pdf`

### 2.4 File Download

```
GET /file-manager/download?fileDiv=...&attachFileId=...&attachFileSeqNo=...&chkFlag=...&screenId=...
Authorization: Bearer <access_token>
```

### 2.5 Error Responses

All errors follow a consistent format:

**Validation Error (400):**
```json
{
  "status": 400,
  "error": "Validation Error",
  "messages": ["userId: userId is required", "appType: appType is required"]
}
```

**Unauthorized (401):**
```json
{
  "error": "Unauthorized",
  "message": "Full authentication is required to access this resource",
  "path": "/message"
}
```

**Rate Limited (429):**
```json
{
  "token": null,
  "message": "Too many login attempts. Please try again later."
}
```

**Backend Service Error (502):**
```json
{
  "status": 502,
  "error": "Backend Service Error",
  "message": "Tuxedo error description"
}
```

**Internal Server Error (500):**
```json
{
  "status": 500,
  "error": "Internal Server Error",
  "message": "An unexpected error occurred"
}
```

### 2.6 Frontend Token Refresh Pattern

Recommended Axios interceptor pattern:

```javascript
// On 401 response:
// 1. Call POST /auth/refresh with stored refreshToken
// 2. If success: store new accessToken + refreshToken, retry original request
// 3. If fail: redirect to login page

axios.interceptors.response.use(
  response => response,
  async error => {
    if (error.response?.status === 401 && !error.config._retry) {
      error.config._retry = true;
      try {
        const res = await axios.post('/auth/refresh', { refreshToken: storedRefreshToken });
        storedAccessToken = res.data.accessToken;
        storedRefreshToken = res.data.refreshToken;  // IMPORTANT: save rotated token
        error.config.headers.Authorization = `Bearer ${storedAccessToken}`;
        return axios(error.config);
      } catch {
        // Refresh failed, redirect to login
        window.location.href = '/login';
      }
    }
    return Promise.reject(error);
  }
);
```

### 2.7 Backend-Frontend Alignment

| Feature | Backend Status | Frontend Action |
|---------|---------------|-----------------|
| Access Token (15 min) | Implemented | Match (store token, send as `Bearer`) |
| Refresh Token (24h) | Implemented | **Adjust** - use refresh flow, not 7-day storage |
| Token Rotation | On each `/auth/refresh` | **Adjust** - always save new refresh token from response |
| Token Blacklist | Implemented (Caffeine) | Match (handle `401` on revoked tokens) |
| Rate Limiting (5/15min) | Implemented | **Add** - show UI message on `429` response |
| HMAC Request Signing | Implemented | Match (send `X-HMAC-Signature` header on all POST requests) |

#### HMAC Request Signing (PCI DSS Integrity)

All **POST** requests must include an `X-HMAC-Signature` header. The backend verifies the request body has not been tampered with.

**Spec:**
| Parameter | Value |
|-----------|-------|
| Algorithm | `HmacSHA256` |
| Key | Shared secret (`hmac.secret.key` in properties) |
| Input | Raw request body bytes |
| Output | Hex-encoded lowercase string |
| Header | `X-HMAC-Signature` |

**Skipped for:** GET requests, `/`, `/actuator/health`

**Frontend example:**
```javascript
async function signRequest(body, hmacSecretKey) {
  const encoder = new TextEncoder();
  const key = await crypto.subtle.importKey(
    "raw", encoder.encode(hmacSecretKey), { name: "HMAC", hash: "SHA-256" }, false, ["sign"]
  );
  const signature = await crypto.subtle.sign("HMAC", key, encoder.encode(JSON.stringify(body)));
  return Array.from(new Uint8Array(signature)).map(b => b.toString(16).padStart(2, '0')).join('');
}

// Usage with Axios:
const body = { encryptedMessage: "...", iv: "..." };
const hmac = await signRequest(body, HMAC_SECRET_KEY);
axios.post('/message', body, {
  headers: {
    'Authorization': `Bearer ${token}`,
    'X-HMAC-Signature': hmac
  }
});
```

**Error if missing or invalid:**
```json
{ "status": 401, "error": "Unauthorized", "message": "HMAC signature is required" }
{ "status": 401, "error": "Unauthorized", "message": "Invalid HMAC signature" }
```

---

## 3. API Endpoints

| Method | Path | Auth | Description |
|--------|------|------|-------------|
| GET | `/` | No | Health check (returns "Version 2.5.1") |
| POST | `/auth/login` | No | Login, returns access token + refresh token |
| POST | `/auth/refresh` | No | Refresh tokens (rotates refresh token) |
| POST | `/auth/logout` | Yes | Blacklists access token, invalidates refresh token |
| GET | `/auth/me` | Yes | Get current user info (returns `userId`) |
| POST | `/message` | Yes | Main gateway: encrypted message to/from Tuxedo |
| POST | `/message/forward/weblogic` | Yes | Raw byte forwarding to Tuxedo |
| POST | `/file-manager/upload` | Yes | Upload files (multipart) |
| GET | `/file-manager/download` | Yes | Download files |

**Public paths:** `/auth/login`, `/auth/refresh`, `/actuator/health`, `/`
**All other paths** require `Authorization: Bearer <token>`

---

## 4. Security Features

| Feature | Details |
|---------|---------|
| JWT Access Token | HS256, 15-minute expiry |
| Refresh Token | UUID, 24-hour expiry, rotation on use |
| Token Blacklist | In-memory (Caffeine), auto-expires with token TTL |
| Login Rate Limiting | 5 attempts per userId, 15-minute lockout |
| Input Validation | `@Valid` + `@NotBlank` on login, refresh, logout, and message DTOs |
| Path Traversal Protection | `extraPath` sanitized via path normalization + base directory validation |
| Security Headers | `X-Content-Type-Options`, `X-Frame-Options: DENY`, `X-XSS-Protection`, `HSTS` |
| CORS | Configurable via `cors.allowed.origins` in profile properties |
| Audit Log | Logs user, method, path, status, duration for every request |
| File Upload | Extension whitelist (`jpg,png,pdf`), path traversal protection |
| HMAC Request Signing | HmacSHA256 on all POST bodies, `X-HMAC-Signature` header |
| Exception Handling | Global `@ControllerAdvice`, never exposes stack traces |

---

## 5. Configuration (Spring Profiles)

```
src/main/resources/
├── application.properties          # Shared config (no secrets)
├── application-dev.properties      # Dev secrets + paths
├── application-prod.properties     # Prod secrets + paths (gitignored)
└── application-uat.properties      # UAT secrets + paths (gitignored)
```

Default active profile: `dev`. Switch via `-Dspring.profiles.active=prod`.

**Shared config (`application.properties`):**

| Property | Value |
|----------|-------|
| `spring.profiles.active` | `dev` |
| `file.ext.filter` | `jpg,png,pdf` |
| `spring.servlet.multipart.max-file-size` | `10MB` |

**Per-profile config (`application-{profile}.properties`):**

| Property | Dev Value | Prod Value |
|----------|-----------|------------|
| `aes.secret.key` | `ABCDEFGHIJKLMNOP` | (real secret) |
| `jwt_secret` | `3ZxNqN1V+...` | (real secret) |
| `hmac.secret.key` | `DevHmacSecretKey1234567890ABCDEF` | (real secret) |
| `file.upload.path` | `D:/bcap` | `/app/bcap` |
| `cors.allowed.origins` | `http://localhost:3000,http://192.168.79.36,...` | `https://your-domain.com` |

---

## 6. Project Structure

```
src/main/java/
├── maas/bcap/
│   ├── BcapApplication.java            # Entry point
│   ├── controller/
│   │   ├── AuthController.java         # /auth/*
│   │   ├── BcapController.java         # / (health check)
│   │   ├── ExampleController.java      # Dev/test endpoints (@Profile("dev") only)
│   │   ├── FileManagerController.java  # /file-manager/*
│   │   ├── MessageController.java      # /message (main gateway)
│   │   └── GlobalExceptionHandler.java # @ControllerAdvice
│   ├── dto/                            # Request/Response objects
│   ├── module/                         # 200+ Tuxedo service wrappers
│   │   ├── ac/ (Accounts)
│   │   ├── au/ (Audit)
│   │   ├── az/ (Authorization)
│   │   ├── ed/ (Education)
│   │   ├── mc/ (Management)
│   │   └── mt/ (Master)
│   ├── security/
│   │   ├── SecurityConfig.java         # Filter chain, CORS, headers
│   │   ├── JwtUtil.java                # JWT generate/validate (HS256)
│   │   ├── JwtAuthFilter.java          # Bearer token extraction + blacklist check
│   │   ├── JwtBlacklist.java           # Caffeine-based token blacklist
│   │   ├── LoginRateLimiter.java       # Caffeine-based rate limiter
│   │   ├── RefreshTokenStore.java      # Caffeine-based refresh token store
│   │   ├── HmacVerificationFilter.java  # HMAC-SHA256 request integrity check
│   │   ├── CachedBodyHttpServletRequest.java # Request body caching for HMAC
│   │   ├── AuditLogFilter.java         # Request audit logging
│   │   └── CustomAuthEntryPoint.java   # 401 response handler
│   └── service/
│       ├── AuthService.java            # Login + refresh + logout logic
│       ├── MessageService.java         # AES encrypt/decrypt + Tuxedo relay
│       └── FileManagerService.java     # File upload/download
│
└── mti/com/                            # Tuxedo integration library
    ├── cipher/SHAEncryption.java       # SHA password hashing
    └── telegram/                       # Byte-level serialization for Tuxedo
```

---

## 7. Tech Stack

| Component | Version | Notes |
|-----------|---------|-------|
| Spring Boot | 2.7.18 | Last version using `javax.*` namespace |
| Java | 21 | Running on WebLogic 14c |
| Security | Spring Security + JWT (jjwt 0.11.5) | Stateless, no sessions |
| Caching | Caffeine 3.1.5 | Rate limiter, token blacklist, refresh tokens |
| Logging | Log4j2 | Rolling daily files |
| Build | Maven (WAR) | Deployed on WebLogic 14c |
| Tuxedo | WebLogic WTC (wls-tuxedo 14.1.2) | CARRAY buffer via SLCFPROXY |
