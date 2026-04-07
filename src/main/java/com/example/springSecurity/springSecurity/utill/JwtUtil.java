package com.example.springSecurity.springSecurity.utill;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

/**
 * Utility class for handling JWT operations.
 *
 * Responsibilities:
 * - Generating JWT access and refresh tokens
 * - Extracting username from JWT
 * - Validating JWT tokens
 *
 * Used by authentication and JWT filter components.
 */
@Component
public class JwtUtil {

    // Secret key for signing JWT tokens (should be stored securely in production)
    private final String SECRET = "mysecretkeymysecretkeymysecretkey123456";

    // Key object used for signing tokens
    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    // Token expiration times
    private final long ACCESS_TOKEN_EXPIRATION = 1000 * 60 * 15;
    private final long REFRESH_TOKEN_EXPIRATION = 1000 * 60 * 24 * 7;

    /**
     * Generates a basic JWT token with username as subject.
     *
     * @param username the username to include in the token
     * @return generated JWT string
     */
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Generates an access token with username and role claims.
     *
     * @param username the username to include in the token
     * @param role user's role to include as a claim
     * @return generated JWT string
     */
    public String generateAccessToken(String username, String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION)) // 1 hour
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }


    /**
     * Extracts the username (subject) from the JWT token.
     *
     * @param token JWT token string
     * @return username from the token
     */
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /**
     * Validates the JWT token.
     *
     * @param token JWT token string
     * @return true if token is valid, false otherwise
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
