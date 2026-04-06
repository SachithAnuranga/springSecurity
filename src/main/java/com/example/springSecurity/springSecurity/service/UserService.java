package com.example.springSecurity.springSecurity.service;

import com.example.springSecurity.springSecurity.dto.UserRequestDto;
import com.example.springSecurity.springSecurity.entity.Users;
import com.example.springSecurity.springSecurity.response.AuthResponse;
import org.springframework.data.domain.Page;

public interface UserService {

    public String userSignUp(UserRequestDto userRequestDto);

    public Page<Users> getUsersList(int page, int size);

    AuthResponse getToken(String userName, String password);

    AuthResponse refreshAccessToken(String userName);
}
