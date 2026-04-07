package com.example.springSecurity.springSecurity.repo;

import com.example.springSecurity.springSecurity.entity.token.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for managing RefreshToken entities.
 *
 * Provides CRUD operations via JpaRepository and
 * additional custom query methods.
 *
 * Used for JWT refresh token management in authentication flow.
 */
@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {


    /**
     * Finds a refresh token entity by its token string.
     *
     * @param token the refresh token string
     * @return an Optional containing the RefreshToken if found,
     *         or empty if not found
     */
    Optional<RefreshToken> findByToken(String token);
}
