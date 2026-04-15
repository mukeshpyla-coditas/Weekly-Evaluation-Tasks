package com.example.mockServerTask.controller;

import com.example.mockServerTask.dto.RegisterResponseDTO;
import com.example.mockServerTask.entity.User;
import com.example.mockServerTask.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public RegisterResponseDTO registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }
}
