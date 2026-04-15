package com.example.mockServerTask.service;

import com.example.mockServerTask.dto.RegisterResponseDTO;
import com.example.mockServerTask.entity.AuthCode;
import com.example.mockServerTask.entity.User;
import com.example.mockServerTask.repository.AuthRepository;
import com.example.mockServerTask.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;
    private final AuthRepository authRepository;

    public RegisterResponseDTO registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_" + user.getRole());
        String authenticationCode = authService.generateAuthCode();
        AuthCode authCode = new AuthCode();
        authCode.setAuthenticationCode(authenticationCode);
        authCode.setIssuedAt(LocalTime.now());
        userRepository.save(user);

        authCode.setUser(user);
        authRepository.save(authCode);

        RegisterResponseDTO responseDTO = new RegisterResponseDTO();
        responseDTO.setUsername(user.getUsername());
        responseDTO.setAuthCode(authenticationCode);
        responseDTO.setTimestamp(LocalDateTime.now());

        return responseDTO;

    }
}
