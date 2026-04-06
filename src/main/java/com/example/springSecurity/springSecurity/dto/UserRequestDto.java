package com.example.springSecurity.springSecurity.dto;

import lombok.Data;

@Data
public class UserRequestDto {

    private String userName;

    private String password;

    private String email;

    private String role;   // ROLE_USER / ROLE_ADMIN
}

