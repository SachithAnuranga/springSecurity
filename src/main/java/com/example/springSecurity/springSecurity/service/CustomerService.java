package com.example.springSecurity.springSecurity.service;

import com.example.springSecurity.springSecurity.dto.CustomerRequestDto;
import com.example.springSecurity.springSecurity.dto.CustomerResponseDto;
import com.example.springSecurity.springSecurity.entity.Customers;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CustomerService {
    String addCustomers(CustomerRequestDto requestDto);

    public List<CustomerResponseDto> getAllCustomers(int page, int size) ;
}
