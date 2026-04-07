package com.example.springSecurity.springSecurity.config;

import com.example.springSecurity.springSecurity.utill.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * This class is responsible for configuring Spring Security for the application.
 *
 * It defines:
 * - Password encoding mechanism
 * - HTTP security rules (authorization & authentication)
 * - JWT-based stateless session management
 * - Custom security filters (JWT filter)
 *
 * @EnableMethodSecurity enables method-level security annotations
 * like @PreAuthorize, @PostAuthorize, etc.
 */
@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    // Injecting custom JWT filter to validate tokens for each request
    private final JwtFilter jwtFilter;

    /**
     * Defines the password encoder bean.
     *
     * BCrypt is used for hashing passwords before storing in DB
     * and for validating user credentials during authentication.
     *
     * @return PasswordEncoder implementation (BCrypt)
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configures the HTTP security for the application.
     *
     * - Disables CSRF (suitable for stateless APIs)
     * - Defines public and secured endpoints
     * - Enforces role-based access control
     * - Configures stateless session (JWT-based authentication)
     * - Adds custom JWT filter before Spring's authentication filter
     *
     * @param http HttpSecurity object to configure security rules
     * @return configured SecurityFilterChain
     * @throws Exception if configuration fails
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/v1/login/getToken",
                                "/api/v1/login/refresh",
                                "/users/api/v1/signUp"
                        ).permitAll()
                        .requestMatchers("/users/api/v1/userList").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


}
