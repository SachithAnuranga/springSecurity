package com.example.springSecurity.springSecurity.service;

import com.example.springSecurity.springSecurity.dto.UserRequestDto;
import com.example.springSecurity.springSecurity.entity.Users;
import com.example.springSecurity.springSecurity.response.AuthResponse;
import org.springframework.data.domain.Page;

/**
 * Service interface for handling all user-related business logic.
 *
 * Responsibilities include:
 * - User registration and validation
 * - Retrieving paginated user lists
 * - Authentication and JWT token management
 *
 * Implementation classes (e.g., UserServiceImpl) handle
 * interactions with repositories and token generation logic.
 */
public interface UserService {

    /**
     * Registers a new user in the system.
     *
     * @param userRequestDto DTO containing username, email, password, and role
     * @return success/failure message
     */
    String userSignUp(UserRequestDto userRequestDto);

    /**
     * Retrieves a paginated list of users.
     *
     * @param page 0-based page index
     * @param size number of records per page
     * @return Page of Users entities
     */
    Page<Users> getUsersList(int page, int size);

    /**
     * Authenticates a user and generates access and refresh tokens.
     *
     * @param userName username of the user
     * @param password user's password
     * @return AuthResponse containing accessToken and refreshToken
     */
    AuthResponse getToken(String userName, String password);

    /**
     * Refreshes a user's access token using the refresh token.
     *
     * @param userName username of the user
     * @return AuthResponse containing a new access token
     */
    AuthResponse refreshAccessToken(String userName);
}
