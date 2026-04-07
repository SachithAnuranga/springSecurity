package com.example.springSecurity.springSecurity.controller;

import com.example.springSecurity.springSecurity.dto.UserRequestDto;
import com.example.springSecurity.springSecurity.entity.Users;
import com.example.springSecurity.springSecurity.pagination.PaginatedResponseUserDto;
import com.example.springSecurity.springSecurity.response.StandardResponse;
import com.example.springSecurity.springSecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This controller handles user management operations.
 *
 * It provides endpoints for:
 * - User registration (sign-up)
 * - Retrieving users with pagination
 *
 * Base URL: /users/api/v1
 */
@RestController
@RequestMapping("users/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Handles user registration.
     *
     * Accepts user details as a request body and creates a new user.
     * Returns a success message if the user is created successfully,
     * otherwise returns an error message (e.g., duplicate username/email).
     *
     * @param userRequestDto user registration data (DTO)
     * @return ResponseEntity containing success or error message
     */
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

    /**
     * Retrieves a paginated list of users.
     *
     * Accepts page number and page size as request parameters,
     * fetches user data from the service layer, and returns it
     * in a standardized response format.
     *
     * @param page page number
     * @param size number of records per page
     * @return ResponseEntity containing paginated user data
     */
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
