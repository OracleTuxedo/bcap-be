package maas.bcap.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import maas.bcap.dto.AuthInfoDto;

@Component
public class AuditLogFilter extends OncePerRequestFilter {

    private static final Logger audit = LogManager.getLogger("AUDIT");

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        long start = System.currentTimeMillis();
        filterChain.doFilter(request, response);
        long duration = System.currentTimeMillis() - start;

        String userId = extractUserId();
        audit.info("AUDIT | user={} | {} {} | status={} | {}ms",
                userId,
                request.getMethod(),
                request.getServletPath(),
                response.getStatus(),
                duration);
    }

    private String extractUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof AuthInfoDto) {
            return ((AuthInfoDto) auth.getPrincipal()).getUserId();
        }
        return "anonymous";
    }
}
