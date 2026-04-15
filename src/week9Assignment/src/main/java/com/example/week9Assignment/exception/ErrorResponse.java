package com.example.week9Assignment.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@AllArgsConstructor
public class ErrorResponse {
    private Integer statusCode;
    private String message;
    private LocalDateTime timestamp;
}
