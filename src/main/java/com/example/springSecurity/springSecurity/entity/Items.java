package com.example.springSecurity.springSecurity.entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * This entity represents an Item (product) in the system.
 *
 * It maps to the "items" table in the database and
 * holds basic item details along with its association
 * with order items.
 *
 * Relationships:
 * - One-to-Many with OrderItem (an item can be part of multiple orders)
 */
@Entity
@Table(name = "items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Items {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment primary key
    private Long id;

    private String name;

    private double price;

    // One customer can have many orders
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;
}