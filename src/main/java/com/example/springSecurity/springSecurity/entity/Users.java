package com.example.springSecurity.springSecurity.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * This entity represents a User account in the system.
 *
 * It maps to the "users" table in the database and stores
 * credentials, role, and account status for authentication
 * and authorization purposes.
 *
 * Used in Spring Security for login, JWT token generation,
 * and role-based access control.
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(unique = true, length = 50)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true, length = 100)
    private String email;


    @Column(nullable = false)
    private String role;   // Example: ROLE_USER, ROLE_ADMIN

    @Column(nullable = false)
    private boolean enabled = true;

    // ✅ Managed by DB
    @CreationTimestamp
    private LocalDateTime createdAt;

    // ✅ Managed by DB
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
