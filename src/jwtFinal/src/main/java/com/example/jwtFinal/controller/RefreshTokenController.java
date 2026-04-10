package com.example.jwtFinal.controller;

import com.example.jwtFinal.dto.RequestRefreshTokenDTO;
import com.example.jwtFinal.dto.TokenResponseDto;
import com.example.jwtFinal.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/refresh-token")
@RequiredArgsConstructor
public class RefreshTokenController {
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/get-access-token")
    public TokenResponseDto generateOrRefreshToken(@RequestBody RequestRefreshTokenDTO requestRefreshTokenDTO) {
        return refreshTokenService.generateRefreshToken(requestRefreshTokenDTO.getRefreshTokenId());
    }
}
