package com.example.jwtFinal.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorResponse {
    private Integer statusCode;
    private String message;
    private LocalDateTime timestamp;
}
