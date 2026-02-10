package maas.bcap.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import maas.bcap.dto.AuthInfoDto;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final JwtBlacklist jwtBlacklist;

    public JwtAuthFilter(JwtUtil jwtUtil, JwtBlacklist jwtBlacklist) {
        this.jwtUtil = jwtUtil;
        this.jwtBlacklist = jwtBlacklist;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            try {
                if (jwtBlacklist.isBlacklisted(token)) {
                    throw new JwtAuthenticationException("Token has been revoked", null);
                }

                if (jwtUtil.validateToken(token)) {
                    AuthInfoDto authInfoDto = jwtUtil.extractAuthInfo(token);

                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            authInfoDto, // now not null
                            null,
                            null);

                    authentication.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (ExpiredJwtException e) {
                throw new JwtAuthenticationException("JWT token expired", e);
            } catch (UnsupportedJwtException e) {
                throw new JwtAuthenticationException("Unsupported JWT token", e);
            } catch (MalformedJwtException e) {
                throw new JwtAuthenticationException("Malformed JWT token", e);
            } catch (JwtException e) {
                throw new JwtAuthenticationException("Invalid JWT token", e);
            }
        }

        // only reach here if no exception was thrown
        filterChain.doFilter(request, response);
    }
}
