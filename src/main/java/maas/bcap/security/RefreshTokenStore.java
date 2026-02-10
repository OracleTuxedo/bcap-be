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

    public void invalidate(String refreshToken) {
        refreshTokens.invalidate(refreshToken);
    }
}
