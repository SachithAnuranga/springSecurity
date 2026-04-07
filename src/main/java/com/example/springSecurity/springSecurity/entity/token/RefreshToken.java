package com.example.springSecurity.springSecurity.entity.token;

import com.example.springSecurity.springSecurity.entity.Users;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * This entity represents a Refresh Token stored in the database.
 *
 * It is used to implement JWT refresh token functionality
 * for stateless authentication.
 *
 * Each refresh token is linked to a specific user and has
 * an expiration date.
 */
@Entity
@Table(name = "refresh_token")
@Data
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String token;

    private LocalDateTime expiryDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;
}
