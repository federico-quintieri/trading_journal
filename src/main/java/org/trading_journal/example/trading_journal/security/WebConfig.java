package org.trading_journal.example.trading_journal.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.lang.NonNull;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NonNull CorsRegistry registry) {
                registry.addMapping("/api/**") // o solo /api/** se vuoi limitare
                        .allowedOrigins("http://localhost:5173")
                        .allowedMethods("*") // GET, POST, PUT, DELETE...
                        .allowedHeaders("*")
                        .allowCredentials(true); // se usi cookie/sessione
            }
        };
    }
}
