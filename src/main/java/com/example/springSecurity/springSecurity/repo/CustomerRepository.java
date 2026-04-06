package com.example.springSecurity.springSecurity.repo;

import com.example.springSecurity.springSecurity.entity.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customers, Long> {
    boolean existsByEmail(String email);
}
