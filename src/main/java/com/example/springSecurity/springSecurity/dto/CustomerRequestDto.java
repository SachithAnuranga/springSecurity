package com.example.springSecurity.springSecurity.dto;
import lombok.*;

/**
 * This DTO is used to transfer customer data from the client
 * to the server (typically in API requests).
 *
 * It represents the data required to create or update a customer.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequestDto {

    private Long id;
    private String name;
    private String email;
}
