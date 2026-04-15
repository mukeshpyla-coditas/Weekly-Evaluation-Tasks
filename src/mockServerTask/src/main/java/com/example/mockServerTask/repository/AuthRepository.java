package com.example.mockServerTask.repository;

import com.example.mockServerTask.entity.AuthCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<AuthCode, Integer> {
    Optional<AuthCode> findByAuthenticationCode(String authCode);
}
