package com.example.jwtFinal.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class TokenResponseDto {
    private String token;
}
