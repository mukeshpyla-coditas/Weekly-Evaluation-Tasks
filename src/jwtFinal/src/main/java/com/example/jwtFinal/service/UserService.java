package com.example.jwtFinal.service;

import com.example.jwtFinal.entity.User;
import com.example.jwtFinal.exception.DuplicateEmailException;
import com.example.jwtFinal.exception.DuplicateUsernameException;
import com.example.jwtFinal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User saveUser(User user) {
        if(checkIfUserExistsViaUsername(user.getUsername())) {
            throw new DuplicateUsernameException("Specified username already exists.");
        }

        if(checkIfUserExistsViaEmail(user.getEmail())) {
            throw new DuplicateEmailException("Specified email already exists.");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_" + user.getRole());
        return userRepository.save(user);
    }

    public boolean checkIfUserExistsViaUsername(String username) {
        if(userRepository.findByUsername(username).isPresent()) {
            return true;
        }

        return false;
    }

    public boolean checkIfUserExistsViaEmail(String email) {
        if(userRepository.findByEmail(email).isPresent()) {
            return true;
        }

        return false;
    }

    public User getUserProfile(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Specified username does not exist."));
    }
}
