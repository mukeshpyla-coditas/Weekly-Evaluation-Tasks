package com.example.jwtFinal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "refresh_token")
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "refresh_token_id", nullable = false, updatable = false, unique = true)
    private String refreshTokenId;

    @Column(name = "issued_at", nullable = false)
    private Date issuedAt;

    @Column(name = "expired_at", nullable = false)
    private Date expiredAt;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;
}
