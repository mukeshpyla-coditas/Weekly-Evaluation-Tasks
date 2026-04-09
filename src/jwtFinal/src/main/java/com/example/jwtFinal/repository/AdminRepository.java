package com.example.jwtFinal.repository;

import com.example.jwtFinal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<User, Integer> {
}
