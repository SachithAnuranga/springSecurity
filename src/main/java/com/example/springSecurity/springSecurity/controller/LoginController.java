package com.example.springSecurity.springSecurity.controller;

import com.example.springSecurity.springSecurity.response.AuthResponse;
import com.example.springSecurity.springSecurity.response.StandardResponse;
import com.example.springSecurity.springSecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller handles authentication-related operations.
 *
 * It provides endpoints for:
 * - Generating JWT access and refresh tokens (login)
 * - Refreshing access tokens using a valid refresh token
 *
 * Base URL: /api/v1/login
 */
@RestController
@RequestMapping("api/v1/login")
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * Handles user login and generates JWT tokens.
     *
     * Accepts username and password as request parameters,
     * validates credentials, and returns access & refresh tokens.
     *
     * @param userName user's username
     * @param password user's password
     * @return ResponseEntity containing authentication response (tokens)
     */
    @PostMapping("/getToken")
    public ResponseEntity<?> login(@RequestParam String userName, @RequestParam String password) {
        AuthResponse authResponse = userService.getToken(userName, password);
        return new ResponseEntity<>(
                new StandardResponse(200, authResponse, "Success"),
                HttpStatus.OK);
    }

    /**
     * Generates a new access token using a valid refresh token.
     *
     * Accepts refresh token as a request parameter and returns
     * a new access token if the refresh token is valid.
     *
     * @param refreshToken valid refresh token
     * @return ResponseEntity containing new access token
     */
    @PostMapping("/refresh")
    public ResponseEntity<?> refreshAccessToken(@RequestParam String refreshToken) {
        AuthResponse authResponse = userService.refreshAccessToken(refreshToken);
        return new ResponseEntity<>(
                new StandardResponse(200, authResponse.getAccessToken(), "Success"),
                HttpStatus.OK);
    }
}
