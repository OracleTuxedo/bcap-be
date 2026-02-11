package maas.bcap.security;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class HmacVerificationFilter extends OncePerRequestFilter {

    private static final Logger log = LogManager.getLogger(HmacVerificationFilter.class);
    private static final String HMAC_HEADER = "X-HMAC-Signature";
    private static final String HMAC_ALGORITHM = "HmacSHA256";

    private static final List<String> SKIP_PATHS = Arrays.asList(
            "/actuator/health",
            "/"
    );

    @Value("${hmac.secret.key}")
    private String hmacSecretKey;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        /// Skip methods that do not carry a request body
        String method = request.getMethod();
        if (HttpMethod.GET.matches(method) || HttpMethod.HEAD.matches(method) || HttpMethod.OPTIONS.matches(method)) {
            filterChain.doFilter(request, response);
            return;
        }

        /// Skip whitelisted paths (getServletPath strips context path e.g. /bcap)
        String path = request.getServletPath();
        for (String skipPath : SKIP_PATHS) {
            if (path.equals(skipPath)) {
                filterChain.doFilter(request, response);
                return;
            }
        }

        /// Wrap request to cache body (so it can be read twice)
        CachedBodyHttpServletRequest cachedRequest = new CachedBodyHttpServletRequest(request);
        byte[] body = cachedRequest.getCachedBody();

        /// Get HMAC signature from header
        String receivedSignature = cachedRequest.getHeader(HMAC_HEADER);
        if (receivedSignature == null || receivedSignature.isEmpty()) {
            log.warn("HMAC signature missing for {} {}", request.getMethod(), path);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"status\":401,\"error\":\"Unauthorized\",\"message\":\"HMAC signature is required\"}");
            return;
        }

        /// Compute expected HMAC
        String expectedSignature = computeHmac(body);
        if (expectedSignature == null || !MessageDigest.isEqual(
                expectedSignature.getBytes(StandardCharsets.UTF_8),
                receivedSignature.getBytes(StandardCharsets.UTF_8))) {
            log.warn("HMAC signature mismatch for {} {}", request.getMethod(), path);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"status\":401,\"error\":\"Unauthorized\",\"message\":\"Invalid HMAC signature\"}");
            return;
        }

        filterChain.doFilter(cachedRequest, response);
    }

    private String computeHmac(byte[] data) {
        try {
            Mac mac = Mac.getInstance(HMAC_ALGORITHM);
            SecretKeySpec keySpec = new SecretKeySpec(
                    hmacSecretKey.getBytes(StandardCharsets.UTF_8), HMAC_ALGORITHM);
            mac.init(keySpec);
            byte[] hmacBytes = mac.doFinal(data);
            return bytesToHex(hmacBytes);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            log.error("HMAC computation failed", e);
            return null;
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
