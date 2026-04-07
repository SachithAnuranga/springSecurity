package com.example.springSecurity.springSecurity.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * This entity represents an Order in the system.
 *
 * It maps to the "orders" table in the database and holds
 * order details along with relationships to customers and items.
 *
 * Relationships:
 * - Many orders belong to one customer
 * - One order can have many OrderItems
 */
@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    private Long id;

    private String productName;

    private double total;

    @Column(nullable = false) // cannot be null
    private LocalDateTime orderDate;

    // Many orders belong to one customer
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customers customer;

    // One customer can have many orders
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;
}