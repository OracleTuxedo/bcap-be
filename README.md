# BCAP Backend

Spring Boot REST API gateway for Next.js frontend to Oracle Tuxedo backend.

## Prerequisites

- Java 21
- Maven (wrapper included)
- Oracle WebLogic 12c (for deployment)

## Setup

### 1. Install Tuxedo JAR

The `wls-tuxedo` JAR is not in Maven Central. Install it from the `jar/` folder:

```shell
.\mvnw install:install-file "-Dfile=jar\com.bea.core.jatmi.jar" "-DgroupId=com.oracle.weblogic" "-DartifactId=wls-tuxedo" "-Dversion=14.1.2" "-Dpackaging=jar"
```

### 2. Build

```shell
.\mvnw clean install -DskipTests
```

### 3. Run Locally

```shell
.\mvnw spring-boot:run
```

Default profile: `dev` (uses `application-dev.properties`).

### 4. Deploy to WebLogic

```shell
.\mvnw clean package -DskipTests
```

Deploy `target/bcap-2.5.war` to WebLogic 12c with:

```
-Dspring.profiles.active=prod
```

## Configuration

Config is split by Spring profiles:

| File | Purpose | In Git? |
|------|---------|---------|
| `application.properties` | Shared config (no secrets) | Yes |
| `application-dev.properties` | Dev secrets + paths | Yes |
| `application-prod.properties` | Prod secrets + paths | No |
| `application-uat.properties` | UAT secrets + paths | No |

### Required Properties (per profile)

| Property | Description |
|----------|-------------|
| `aes.secret.key` | AES-128 encryption key (16 chars) |
| `jwt_secret` | JWT HS256 signing key |
| `hmac.secret.key` | HMAC-SHA256 request signing key |
| `file.upload.path` | Disk path for file uploads |
| `cors.allowed.origins` | Allowed CORS origins (comma-separated) |

## API Overview

| Method | Path | Auth | Description |
|--------|------|------|-------------|
| GET | `/` | No | Health check |
| POST | `/auth/login` | No | Login (returns access + refresh token) |
| POST | `/auth/refresh` | No | Refresh tokens |
| POST | `/auth/logout` | Yes | Logout (blacklists tokens) |
| POST | `/message` | Yes | Encrypted message gateway to Tuxedo |
| POST | `/file-manager/upload` | Yes | File upload (multipart) |
| GET | `/file-manager/download` | Yes | File download |

All POST requests require `X-HMAC-Signature` header (HMAC-SHA256 of request body).

See [CONTEXT.md](CONTEXT.md) for full frontend integration guide.
