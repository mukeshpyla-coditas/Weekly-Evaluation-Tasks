package com.example.week9Assignment.controller;

import com.example.week9Assignment.entity.User;
import com.example.week9Assignment.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/super-admin")
@RequiredArgsConstructor
public class SuperAdminController {
    private final UserService userService;

    @PostMapping("/create/admin")
    public User registerAdmin(@RequestBody User user) {
        return userService.saveUser(user);
    }
}
