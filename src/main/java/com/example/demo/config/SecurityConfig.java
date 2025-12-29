package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Disable CSRF for simplicity (Swagger + APIs)
            .csrf(csrf -> csrf.disable())
            // Authorize requests
            .authorizeHttpRequests(auth -> auth
                // Allow Swagger/OpenAPI endpoints
                .requestMatchers(
                    "/v3/api-docs/**",
                    "/swagger-ui/**",
                    "/swagger-ui.html"
                ).permitAll()
                // Allow authentication endpoints
                .requestMatchers("/auth/**").permitAll()
                // Allow templates endpoint (fixes your 403)
                .requestMatchers("/templates/**").permitAll()
                // Everything else requires authentication
                .anyRequest().authenticated()
            )
            // Basic HTTP security defaults
            .httpBasic();

        return http.build();
    }
}
