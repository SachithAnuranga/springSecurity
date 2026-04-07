package com.example.springSecurity.springSecurity.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * This DTO is used to send authentication tokens
 * back to the client after a successful login or refresh.
 *
 * Contains:
 * - Access token (short-lived, used for API requests)
 * - Refresh token (long-lived, used to generate new access tokens)
 */
@Data
@AllArgsConstructor
public class AuthResponse {
    private String accessToken;
    private String refreshToken;
}
