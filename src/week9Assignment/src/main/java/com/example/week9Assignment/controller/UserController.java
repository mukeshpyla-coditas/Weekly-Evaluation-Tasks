package com.example.week9Assignment.controller;

import com.example.week9Assignment.entity.User;
import com.example.week9Assignment.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/get-profile/{id}")
    public User getProfile(@PathVariable Integer id) {
        return userService.getUserById(id);
    }
}
