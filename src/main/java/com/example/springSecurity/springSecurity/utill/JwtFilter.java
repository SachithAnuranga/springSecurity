package com.example.springSecurity.springSecurity.utill;

import com.example.springSecurity.springSecurity.entity.Users;
import com.example.springSecurity.springSecurity.repo.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;


/**
 * JWT Filter that intercepts incoming HTTP requests to validate JWT tokens.
 *
 * Responsibilities:
 * - Skip authentication for public endpoints (login, refresh, signup)
 * - Extract and validate JWT from Authorization header
 * - Set authentication in Spring Security context for secured endpoints
 *
 * Extends OncePerRequestFilter to ensure filter runs once per request.
 */
@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;


    /**
     * Constructor for dependency injection.
     *
     * @param jwtUtil JWT utility for token operations
     * @param userRepository Repository to load user details
     */
    public JwtFilter(JwtUtil jwtUtil, UserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    // Public URLs that do not require JWT authentication
    private static final String[] PUBLIC_URLS = {
            "/api/v1/login/getToken",
            "/api/v1/login/refresh",
            "/users/api/v1/signUp"
    };

    /**
     * Intercepts incoming requests and validates JWT if present.
     *
     * @param request incoming HTTP request
     * @param response HTTP response
     * @param filterChain filter chain to continue request processing
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getServletPath();

        // Skip JWT check for public endpoints
        if (path.equals("/api/v1/login/getToken") ||
                path.equals("/api/v1/login/refresh") ||
                path.equals("/users/api/v1/signUp")) {
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            if (jwtUtil.validateToken(token)) {
                String username = jwtUtil.extractUsername(token);
                Users users = userRepository.findByUserName(username);

                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(
                                username,
                                null,
                                Collections.singletonList(new SimpleGrantedAuthority(users.getRole()))
                        );
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        // Continue filter chain
        filterChain.doFilter(request, response);
    }
}