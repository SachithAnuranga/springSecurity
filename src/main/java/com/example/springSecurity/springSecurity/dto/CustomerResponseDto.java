package com.example.springSecurity.springSecurity.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponseDto {

    private Long id;
    private String name;
    private String email;
    // Do not include orders if you want DTO to reflect only table fields
}
