package com.example.springSecurity.springSecurity.dto;

import lombok.Data;

/**
 * This DTO is used to transfer user registration data
 * from the client to the server (typically for sign-up API).
 *
 * It contains all necessary information to create a new user
 * with credentials and assigned role.
 */
@Data
public class UserRequestDto {

    private String userName;

    private String password;

    private String email;

    private String role;   // ROLE_USER / ROLE_ADMIN
}

