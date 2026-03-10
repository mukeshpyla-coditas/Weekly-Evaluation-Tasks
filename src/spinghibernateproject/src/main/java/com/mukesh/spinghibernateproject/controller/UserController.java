package com.mukesh.spinghibernateproject.controller;

import com.mukesh.spinghibernateproject.entity.User;
import com.mukesh.spinghibernateproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("/")
    public String welcomeMessage() {
        return "Welcome to the Login/Registration Page...";
    }

    @PostMapping("/api/register")
    public String registerUser(@RequestBody User user) {
        return service.registerUser(user);
    }

    @PostMapping("/api/login")
    public String loginUser(@RequestBody User user) {
        if(user.getUsername() == null) return service.loginUserByEmail(user);
        else return service.loginUserByUsername(user);
    }

}
