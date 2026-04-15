package com.example.week9Assignment.controller;

import com.example.week9Assignment.dto.LoginRequestDTO;
import com.example.week9Assignment.dto.LoginResponseDTO;
import com.example.week9Assignment.entity.User;
import com.example.week9Assignment.service.UserService;
import com.example.week9Assignment.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/create")
    public User registerUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword())
            );
            User existingUser = userService.getUser(loginRequestDTO.getUsername());
            if(existingUser == null) {
                throw new UsernameNotFoundException("Username not found");
            }
            String jwtToken = jwtUtil.generateToken(loginRequestDTO.getUsername(), loginRequestDTO.getRole());
            return new LoginResponseDTO(jwtToken);

        } catch(Exception e) {
            log.error("Exception occurred is: " + e.getMessage());
            throw new RuntimeException("There is an exception occurred");
        }
    }
}
