package com.example.jwtFinal.controller;

import com.example.jwtFinal.dto.LoginRequestDTO;
import com.example.jwtFinal.dto.RegisterRequestDTO;
import com.example.jwtFinal.dto.RegisterResponseDTO;
import com.example.jwtFinal.dto.TokenResponseDto;
import com.example.jwtFinal.entity.RefreshToken;
import com.example.jwtFinal.entity.User;
import com.example.jwtFinal.exception.InvalidAuthenticationException;
import com.example.jwtFinal.service.RefreshTokenService;
import com.example.jwtFinal.service.UserService;
import com.example.jwtFinal.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/signup")
    public RegisterResponseDTO createUser(@RequestBody RegisterRequestDTO requestDTO) {
        User user = new User();
        user.setUsername(requestDTO.getUsername());
        user.setPassword(requestDTO.getPassword());
        user.setEmail(requestDTO.getEmail());
        user.setRole(requestDTO.getRole());

        User registeredUser =  userService.saveUser(user);
        RegisterResponseDTO responseDTO = new RegisterResponseDTO();
        responseDTO.setUsername(registeredUser.getUsername());
        responseDTO.setMessage("User registered successfully!");


        log.info("User successfully registered!!");
        return responseDTO;
    }

    @PostMapping("/login")
    public TokenResponseDto generate(@RequestBody LoginRequestDTO loginRequestDTO) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword())
            );

            RefreshToken refreshToken = refreshTokenService.saveRefreshToken(loginRequestDTO.getUsername());
            String token = jwtUtil.generateToken(loginRequestDTO.getUsername(), loginRequestDTO.getRole());
            TokenResponseDto tokenResponseDto = new TokenResponseDto();
            tokenResponseDto.setAccessToken(token);
            tokenResponseDto.setRefreshTokenId(refreshToken.getRefreshTokenId());

            return tokenResponseDto;
        } catch(Exception exp) {
            log.error("Exception occurred: " + exp.getMessage());
            throw new InvalidAuthenticationException("Exception occurred while authenticating the user with username: " + loginRequestDTO.getUsername());
        }
    }
}
