package com.example.springSecurity.springSecurity.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This DTO is used to transfer customer data from the server
 * to the client (typically in API responses).
 *
 * It represents the customer information without exposing
 * unnecessary internal entities or relationships.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponseDto {

    private Long id;
    private String name;
    private String email;
    // Do not include orders if you want DTO to reflect only table fields
}
