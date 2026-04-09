package com.example.jwtFinal.service;

import com.example.jwtFinal.entity.User;
import com.example.jwtFinal.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;

    public List<User> getAllUsers() {
        return adminRepository.findAll();
    }
}
