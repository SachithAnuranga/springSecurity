package com.example.springSecurity.springSecurity.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * This entity represents an OrderItem in the system.
 *
 * It acts as a join entity between Orders and Items,
 * allowing a many-to-many relationship with additional
 * attributes like quantity and amount.
 *
 * Relationships:
 * - Many OrderItems belong to one Order
 * - Many OrderItems reference one Item
 */
@Entity
@Table(name = "order_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;

    private int quantity;

    @Column(nullable = false)
    private double amount;

    // Many OrderItems belong to ONE Order
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Orders order;

    // Many OrderItems reference ONE Item
    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Items item;
}
