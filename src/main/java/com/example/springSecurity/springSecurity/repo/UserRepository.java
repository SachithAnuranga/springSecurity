package com.example.springSecurity.springSecurity.repo;

import com.example.springSecurity.springSecurity.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {


    boolean existsByUserName(String userName);

    boolean existsByEmail(String email);

    Users findByUserName(String userName);
}
