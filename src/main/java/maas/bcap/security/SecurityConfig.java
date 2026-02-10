package maas.bcap.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {

    private final HmacVerificationFilter hmacVerificationFilter;
    private final JwtAuthFilter jwtAuthFilter;
    private final AuditLogFilter auditLogFilter;
    private final CustomAuthEntryPoint customAuthEntryPoint;

    @Value("${cors.allowed.origins}")
    private String allowedOrigins;

    public SecurityConfig(HmacVerificationFilter hmacVerificationFilter, JwtAuthFilter jwtAuthFilter, AuditLogFilter auditLogFilter, CustomAuthEntryPoint customAuthEntryPoint) {
        this.hmacVerificationFilter = hmacVerificationFilter;
        this.jwtAuthFilter = jwtAuthFilter;
        this.auditLogFilter = auditLogFilter;
        this.customAuthEntryPoint = customAuthEntryPoint;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .headers(headers -> headers
                        .contentTypeOptions(contentType -> {})
                        .frameOptions(frame -> frame.deny())
                        .xssProtection(xss -> {})
                        .httpStrictTransportSecurity(hsts -> hsts
                                .includeSubDomains(true)
                                .maxAgeInSeconds(31536000))
                )
                .exceptionHandling(eh -> eh.authenticationEntryPoint(customAuthEntryPoint))
                .authorizeHttpRequests(auth -> auth
                        .antMatchers("/auth/login", "/auth/refresh").permitAll()
                        .antMatchers("/actuator/health").permitAll()
                        .antMatchers("/").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(hmacVerificationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(jwtAuthFilter, HmacVerificationFilter.class)
                .addFilterAfter(auditLogFilter, JwtAuthFilter.class)
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration cfg = new CorsConfiguration();
        cfg.setAllowedOrigins(Arrays.asList(allowedOrigins.split(",")));
        cfg.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        cfg.setAllowedHeaders(Arrays.asList("*"));
        cfg.setExposedHeaders(Arrays.asList("Authorization"));
        cfg.setAllowCredentials(false);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", cfg);
        return source;
    }
}
