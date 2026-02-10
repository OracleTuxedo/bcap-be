package maas.bcap.security;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

@Component
public class JwtBlacklist {

    private final Cache<String, Boolean> blacklist = Caffeine.newBuilder()
            .expireAfterWrite(15, TimeUnit.MINUTES)
            .maximumSize(50_000)
            .build();

    public void blacklist(String token) {
        blacklist.put(token, true);
    }

    public boolean isBlacklisted(String token) {
        return blacklist.getIfPresent(token) != null;
    }
}
