package maas.bcap.security;

import java.security.Key;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import javax.annotation.PostConstruct;
import maas.bcap.dto.AuthInfoDto;

@Component
public class JwtUtil {
    @Value("${jwt_secret}")
    private String secret;

    private Key key;

    private final long expiration = 1000 * 60 * 15; // 15 Minutes

    private final ObjectMapper mapper = new ObjectMapper();

    @PostConstruct
    public void init(){
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(AuthInfoDto authInfoDto) throws IllegalArgumentException {
        Map<String, Object> authInfoMap = mapper.convertValue(authInfoDto, Map.class);

        return Jwts.builder()
                .claim("AuthInfo", authInfoMap)
                .setSubject(authInfoDto.getUserId())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public AuthInfoDto extractAuthInfo(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return mapper.convertValue(claims.get("AuthInfo"), AuthInfoDto.class);
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean validateToken(String token) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException {
        Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
        return true;
    }
}
