package com.example.springSecurity.springSecurity.response;

import lombok.*;

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
