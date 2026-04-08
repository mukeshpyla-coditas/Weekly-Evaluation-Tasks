package com.example.jwtTask1.controller;

import com.example.jwtTask1.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final JWTUtil jwtUtil;

    @GetMapping("/generate-token")
    public String generatingToken(@RequestParam(name = "username") String username) {
        String jwtToken = jwtUtil.generateToken(username);
        System.out.println("Generated Json Web Token based on the username provided: " + jwtToken);
        return jwtToken;
    }
}
