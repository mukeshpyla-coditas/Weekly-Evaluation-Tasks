package com.example.mockServerTask.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponseDTO {
    private String username;
    private String authCode;
    private LocalDateTime timestamp;
}