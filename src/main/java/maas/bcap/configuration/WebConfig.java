package maas.bcap.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    @NonNull
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NonNull CorsRegistry registry) {
                registry.addMapping("/**")  // Allow CORS on all endpoints
                    .allowedOrigins("http://localhost:5173", "http://localhost:3000")  // Allow the specific origin
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Allow specific methods
                    .allowedHeaders("*")  // Allow all headers
                    .allowCredentials(true);  // Allow credentials (cookies, authorization headers)
            }
        };
    }
}
