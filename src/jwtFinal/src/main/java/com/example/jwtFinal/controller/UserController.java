package com.example.jwtFinal.controller;

import com.example.jwtFinal.entity.User;
import com.example.jwtFinal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/profile")
    public User getProfile(@RequestParam(name = "username") String username) {
        return userService.getUserProfile(username);
    }
}
