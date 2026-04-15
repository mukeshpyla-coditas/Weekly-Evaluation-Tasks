package com.example.mockServerTask.controller;

import com.example.mockServerTask.dto.AccessTokenRequestDTO;
import com.example.mockServerTask.dto.AccessTokenResponse;
import com.example.mockServerTask.dto.UserDetailsResponseDTO;
import com.example.mockServerTask.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping("/request/access-token")
    public AccessTokenResponse generateAccessToken(@RequestBody AccessTokenRequestDTO requestDTO) {
        return authService.getAccessToken(requestDTO.getAuthCode());
    }

    @GetMapping("/request/data")
    public UserDetailsResponseDTO getUserDetails() {
        return authService.getUserDetails();
    }
}
