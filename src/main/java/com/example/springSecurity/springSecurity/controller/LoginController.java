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

@RestController
@RequestMapping("api/v1/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/getToken")
    public ResponseEntity<?> login(@RequestParam String userName, @RequestParam String password) {
        AuthResponse authResponse = userService.getToken(userName, password);
        return new ResponseEntity<>(
                new StandardResponse(200, authResponse, "Success"),
                HttpStatus.OK);
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshAccessToken(@RequestParam String refreshToken) {
        AuthResponse authResponse = userService.refreshAccessToken(refreshToken);
        return new ResponseEntity<>(
                new StandardResponse(200, authResponse.getAccessToken(), "Success"),
                HttpStatus.OK);
    }
}
