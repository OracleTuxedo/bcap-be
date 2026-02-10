package maas.bcap.security;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

@Component
public class LoginRateLimiter {

    private static final int MAX_ATTEMPTS = 5;

    private final Cache<String, Integer> attempts = Caffeine.newBuilder()
            .expireAfterWrite(15, TimeUnit.MINUTES)
            .maximumSize(10_000)
            .build();

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
