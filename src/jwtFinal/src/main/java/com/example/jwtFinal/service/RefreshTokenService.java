package com.example.jwtFinal.service;

import com.example.jwtFinal.dto.TokenResponseDto;
import com.example.jwtFinal.entity.RefreshToken;
import com.example.jwtFinal.entity.User;
import com.example.jwtFinal.repository.RefreshTokenRepository;
import com.example.jwtFinal.repository.UserRepository;
import com.example.jwtFinal.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public RefreshToken saveRefreshToken(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Specified user is not found"));

        String refreshTokenId = getRefreshTokenId();
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setRefreshTokenId(refreshTokenId);
        refreshToken.setUser(user);
        refreshToken.setIssuedAt(new Date());
        refreshToken.setExpiredAt(new Date(System.currentTimeMillis() + (1000 * 60 * 3)));

        return refreshTokenRepository.save(refreshToken);
    }

    public TokenResponseDto generateRefreshToken(String refreshTokenId) {
        RefreshToken refreshToken = refreshTokenRepository.findByRefreshTokenId(refreshTokenId)
                .orElseThrow(() -> new RuntimeException("Specified refresh-token-id is not present"));

        if(refreshToken.getExpiredAt().before(new Date())) {
            User user = refreshToken.getUser();
            refreshTokenRepository.delete(refreshToken);

            String accessToken = jwtUtil.generateToken(user.getUsername(), user.getRole());
            RefreshToken newRefreshToken = new RefreshToken();
            newRefreshToken.setRefreshTokenId(getRefreshTokenId());
            newRefreshToken.setUser(user);
            newRefreshToken.setIssuedAt(new Date());
            newRefreshToken.setExpiredAt(new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24)));
            refreshTokenRepository.save(newRefreshToken);

            TokenResponseDto tokenResponseDto = new TokenResponseDto();
            tokenResponseDto.setAccessToken(accessToken);
            tokenResponseDto.setRefreshTokenId(newRefreshToken.getRefreshTokenId());

            return tokenResponseDto;
        }
        else return null;
    }

    public String getRefreshTokenId() {
        return UUID.randomUUID().toString();
    }
}
