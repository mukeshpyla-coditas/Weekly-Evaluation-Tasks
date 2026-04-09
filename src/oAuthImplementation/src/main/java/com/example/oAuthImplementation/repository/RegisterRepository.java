package com.example.oAuthImplementation.repository;

import com.example.oAuthImplementation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterRepository extends JpaRepository<User, Integer> {
    public User findByUsername(String username);
}
