package org.trading_journal.example.trading_journal.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.*;
// Classi di configurazione che implementa WebMvcConfigurer
@Configuration
public class WebConfig implements WebMvcConfigurer {

    // Implementiamo il metodo che ci serve per stabilire la politica Cors
    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:5173")
                .allowedMethods("GET")
                .allowedHeaders("*");
    }
}