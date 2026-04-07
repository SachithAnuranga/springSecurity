package com.example.springSecurity.springSecurity.response;

import lombok.*;

/**
 * This DTO is used as a standard API response format.
 *
 * Provides a consistent structure for all API responses:
 * - `code`: HTTP status or custom response code
 * - `data`: actual payload (can be any type, including DTOs, lists, or primitives)
 * - `messages`: success/error messages or description
 *
 * Useful for standardizing API responses across the application.
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StandardResponse {
    private int code;
    private Object data;
    private String messages;
}
