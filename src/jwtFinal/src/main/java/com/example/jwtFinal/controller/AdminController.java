package com.example.jwtFinal.controller;

import com.example.jwtFinal.entity.User;
import com.example.jwtFinal.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/getAll")
    public List<User> getAllUsers() {
        return adminService.getAllUsers();
    }
}
