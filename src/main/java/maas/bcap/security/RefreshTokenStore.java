package maas.bcap.security;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

@Component
public class RefreshTokenStore {

    private final Cache<String, String> refreshTokens = Caffeine.newBuilder()
            .expireAfterWrite(24, TimeUnit.HOURS)
            .maximumSize(50_000)
            .build();

    public String generateRefreshToken(String userId) {
        String token = UUID.randomUUID().toString();
        refreshTokens.put(token, userId);
        return token;
    }

    public String validateAndGetUserId(String refreshToken) {
        return refreshTokens.getIfPresent(refreshToken);
    }

    /**
     * Atomically validates and invalidates a refresh token.
     * Returns the userId if the token was valid, null otherwise.
     */
    public String validateAndInvalidate(String refreshToken) {
        return refreshTokens.asMap().remove(refreshToken);
    }

    public void invalidate(String refreshToken) {
        refreshTokens.invalidate(refreshToken);
    }
}
