package com.example.springSecurity.springSecurity.repo;

import com.example.springSecurity.springSecurity.entity.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This repository provides CRUD operations for the Customers entity.
 *
 * Extends JpaRepository which provides:
 * - Basic CRUD operations (save, findById, findAll, delete, etc.)
 * - Paging and sorting support
 *
 * Custom methods can be added for specific queries.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customers, Long> {

    /**
     * Checks if a customer exists with the given email.
     *
     * @param email Email to check for existence
     * @return true if a customer with the email exists, false otherwise
     */
    boolean existsByEmail(String email);
}
