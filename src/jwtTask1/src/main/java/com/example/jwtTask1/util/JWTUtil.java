package com.example.jwtTask1.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JWTUtil {
    private final String secret = "my-app-contains-secret-key-mukesh-narayana-1234567890!@#$%^&*()";

    private final SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());

    public String generateToken(String username) {
        return Jwts.builder()
                .claims()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() * 60 * 60 * 10))
                .and()
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}
