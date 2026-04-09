package com.example.oAuthImplementation.controller;

import com.example.oAuthImplementation.service.RegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class RegisterController {
    private final RegisterService registerService;

    @GetMapping("/public")
    public String publicApi() {
        return "This is a public API";
    }

    @GetMapping("/private")
    public String privateApi(OAuth2AuthenticationToken token) {
        String username = token.getPrincipal().getAttribute("name");
        String email = token.getPrincipal().getAttribute("email");

        boolean status = registerService.saveUserIfNotExists(username, email);
        if(!status) {
            log.warn("USER already exists. Can access the resources.");
        }
        if(status) {
            log.info("USER registered successfully. Can continue to access the resources");
        }

        return "Username: " + username + " Email: " + email;
    }
}
