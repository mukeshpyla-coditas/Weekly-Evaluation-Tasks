package com.example.mockServerTask.service;

import com.example.mockServerTask.dto.AccessTokenResponse;
import com.example.mockServerTask.dto.UserDetailsResponseDTO;
import com.example.mockServerTask.entity.AuthCode;
import com.example.mockServerTask.entity.User;
import com.example.mockServerTask.exception.AuthCodeInValidException;
import com.example.mockServerTask.repository.AuthRepository;
import com.example.mockServerTask.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;
    private final JwtUtil jwtUtil;

    public String generateAuthCode() {
        return UUID.randomUUID().toString();
    }

    public AccessTokenResponse getAccessToken(String authCode) {
        AuthCode existingAuthCode = authRepository.findByAuthenticationCode(authCode)
                .orElseThrow(() -> new AuthCodeInValidException("Specified authCode is invalid"));

        User existingUser = existingAuthCode.getUser();

        String accessToken = jwtUtil.generateJwtToken(existingUser.getUsername(), existingUser.getRole());

        AccessTokenResponse response = new AccessTokenResponse();
        response.setUsername(existingUser.getUsername());
        response.setAuthCode(authCode);
        response.setAccessToken(accessToken);

        return response;
    }

    public UserDetailsResponseDTO getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userDetails = (User) authentication.getPrincipal();

        UserDetailsResponseDTO responseDTO = new UserDetailsResponseDTO();
        responseDTO.setName(userDetails.getUsername());
        responseDTO.setEmail(userDetails.getEmail());
        responseDTO.setGender(userDetails.getGender().toString());
        responseDTO.setDateOfBirth(userDetails.getDateOfBirth());
        responseDTO.setRole(userDetails.getRole());

        return responseDTO;
    }
}
