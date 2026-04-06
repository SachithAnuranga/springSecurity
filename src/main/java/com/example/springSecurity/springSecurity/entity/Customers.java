package com.example.springSecurity.springSecurity.entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(
        name = "customers",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email")  // prevents duplicate emails
        }
)

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    private Long id;

    @Column(nullable = false) // cannot be null
    private String name;

    @Column(nullable = false, unique = true) // cannot be null and unique
    private String email;

    // One customer can have many orders
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Orders> orders;


}