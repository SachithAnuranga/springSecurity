package com.example.springSecurity.springSecurity.service.impl;

import com.example.springSecurity.springSecurity.dto.UserRequestDto;
import com.example.springSecurity.springSecurity.entity.Users;
import com.example.springSecurity.springSecurity.entity.token.RefreshToken;
import com.example.springSecurity.springSecurity.repo.RefreshTokenRepository;
import com.example.springSecurity.springSecurity.repo.UserRepository;
import com.example.springSecurity.springSecurity.response.AuthResponse;
import com.example.springSecurity.springSecurity.service.UserService;
import com.example.springSecurity.springSecurity.utill.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    private final RefreshTokenRepository refreshTokenRepository;


    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           JwtUtil jwtUtil,
                           PasswordEncoder passwordEncoder,
                           RefreshTokenRepository refreshTokenRepository) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Override
    public String userSignUp(UserRequestDto userRequestDto) {

        Users user = Users.builder()
                .userName(userRequestDto.getUserName())
                .email(userRequestDto.getEmail())
                .password(passwordEncoder.encode(userRequestDto.getPassword()))
                .role(userRequestDto.getRole())
                .enabled(true)
                .build();

        // 1️⃣ Check duplicate username
        if (userRepository.existsByUserName(user.getUserName())) {
            throw new RuntimeException("Username already exists");
        }

        // 2️⃣ Check duplicate email
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }


        // 4️⃣ Save
        userRepository.save(user);

        return "User registered successfully";
    }

    @Override
    public Page<Users> getUsersList(int page, int size) {
        return userRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public AuthResponse getToken(String userName, String password) {
        Users users = userRepository.findByUserName(userName);

        if (users == null || !passwordEncoder.matches(password, users.getPassword())) {
            throw new RuntimeException("Invalid Credentials");
        }


        String accessToken = jwtUtil.generateAccessToken(userName, users.getRole());
        String refreshTokenString = UUID.randomUUID().toString();
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(refreshTokenString);
        refreshToken.setUser(users);
        refreshToken.setExpiryDate(LocalDateTime.now().plusDays(7));

        refreshTokenRepository.save(refreshToken);

        // Generate JWT here
        return new AuthResponse(accessToken, refreshTokenString);
    }

    @Override
    public AuthResponse refreshAccessToken(String refreshToken) {
        RefreshToken token = refreshTokenRepository.findByToken(refreshToken).orElseThrow(
                ()-> new RuntimeException("Invalid Refresh Token"));
        if(token.getExpiryDate().isBefore(LocalDateTime.now())){
            refreshTokenRepository.delete(token);

            throw new RuntimeException("Refresh token expired");
        }
        Users users = token.getUser();

        String newAccessToken = jwtUtil.generateAccessToken(users.getUserName(), users.getRole());

        return new AuthResponse(newAccessToken, refreshToken);
    }
}
