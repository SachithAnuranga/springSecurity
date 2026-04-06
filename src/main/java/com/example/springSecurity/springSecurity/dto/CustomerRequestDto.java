package com.example.springSecurity.springSecurity.dto;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequestDto {

    private Long id;
    private String name;
    private String email;
}
