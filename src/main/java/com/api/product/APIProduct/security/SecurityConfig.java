package com.api.product.APIProduct.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll() // Permitir acceso a los endpoints de autenticación
                        .requestMatchers("/admin/**").hasRole("ADMIN") // Requiere el rol ADMIN
                        .requestMatchers("/user/**").hasRole("USER")   // Requiere el rol USER
                        .requestMatchers("/products/**","/addProducts/**","/updateProduct/**","/deleteProduct/**")
                        .hasRole("USER")
                        .anyRequest().authenticated()                 // Cualquier otro endpoint requiere autenticación
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
