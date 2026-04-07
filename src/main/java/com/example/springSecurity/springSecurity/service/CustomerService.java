package com.example.springSecurity.springSecurity.service;

import com.example.springSecurity.springSecurity.dto.CustomerRequestDto;
import com.example.springSecurity.springSecurity.dto.CustomerResponseDto;


import java.util.List;

/**
 * Service interface for managing Customer-related business logic.
 *
 * Defines methods for creating and retrieving customers.
 * Implemented by a service class (e.g., CustomerServiceImpl)
 * to encapsulate business rules and interact with the repository layer.
 */
public interface CustomerService {

    /**
     * Adds a new customer to the system.
     *
     * @param requestDto DTO containing customer details (name, email, etc.)
     * @return a success or failure message
     */
    String addCustomers(CustomerRequestDto requestDto);

    /**
     * Retrieves a paginated list of customers.
     *
     * @param page page number (0-based index)
     * @param size number of records per page
     * @return list of CustomerResponseDto representing the customers
     */
    public List<CustomerResponseDto> getAllCustomers(int page, int size) ;
}
