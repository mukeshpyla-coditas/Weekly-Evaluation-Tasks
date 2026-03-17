package com.example.MappingTask.exception;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    // HTTP status code
    private int statusCode;
    // Response Message
    private String message;
    // time stamp;
    private LocalDateTime timestamp;
}

