package com.example.springSecurity.springSecurity.controller;

import com.example.springSecurity.springSecurity.dto.UserRequestDto;
import com.example.springSecurity.springSecurity.entity.Users;
import com.example.springSecurity.springSecurity.pagination.PaginatedResponseUserDto;
import com.example.springSecurity.springSecurity.response.StandardResponse;
import com.example.springSecurity.springSecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("users/api/v1")
public class UserController {

    @Autowired
    private UserService userService;


    // 🔹 Sign Up API
    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(@RequestBody UserRequestDto userRequestDto) {
        try {
            String message = userService.userSignUp(userRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(message);
        } catch (RuntimeException ex) {
            // Handle exceptions (like duplicate username/email)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping(path = "/userList", params = {"page", "size"})
    public ResponseEntity<StandardResponse> getUserLis(@RequestParam int page, @RequestParam int size) {
        try {
            Page<Users> usersList = userService.getUsersList(page, size);
            PaginatedResponseUserDto responseUserDto = new PaginatedResponseUserDto();
            responseUserDto.setUserDetailList(usersList.getContent()); // set the list
            responseUserDto.setDataCount(usersList.getSize()); // optional: set data count

            return new ResponseEntity<StandardResponse>(new StandardResponse(200, responseUserDto,
                    "User retrieved successfully"), HttpStatus.OK);
        } catch (RuntimeException ex) {
            // Handle exceptions (like duplicate username/email)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
