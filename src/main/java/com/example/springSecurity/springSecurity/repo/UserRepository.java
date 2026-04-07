package com.example.springSecurity.springSecurity.repo;

import com.example.springSecurity.springSecurity.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Users entities.
 *
 * Provides CRUD operations via JpaRepository and
 * additional custom query methods for user management.
 *
 * Used for authentication, registration, and validation logic.
 */
@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

    /**
     * Checks if a user exists with the given username.
     *
     * @param userName username to check
     * @return true if a user with the username exists, false otherwise
     */
    boolean existsByUserName(String userName);

    /**
     * Checks if a user exists with the given email.
     *
     * @param email email to check
     * @return true if a user with the email exists, false otherwise
     */
    boolean existsByEmail(String email);

    /**
     * Finds a user entity by its username.
     *
     * @param userName username to search for
     * @return Users entity with the given username
     */
    Users findByUserName(String userName);
}
