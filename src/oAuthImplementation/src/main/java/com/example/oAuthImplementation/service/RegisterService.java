package com.example.oAuthImplementation.service;

import com.example.oAuthImplementation.entity.User;
import com.example.oAuthImplementation.repository.RegisterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegisterService {
    private final RegisterRepository registerRepository;

    public boolean saveUserIfNotExists(String username, String email) {
        log.info("Username: " + username);
        log.info("Email: " + email);

        if(registerRepository.findByUsername(username) != null) {
            return false;
        }

        User user = new User();
        user.setEmail(email);
        user.setUsername(username);

        registerRepository.save(user);
        return true;
    }
}
