package com.example.springSecurity.springSecurity.service.impl;

import com.example.springSecurity.springSecurity.dto.CustomerRequestDto;
import com.example.springSecurity.springSecurity.dto.CustomerResponseDto;
import com.example.springSecurity.springSecurity.entity.Customers;
import com.example.springSecurity.springSecurity.pagination.PaginationResponseCustomerDto;
import com.example.springSecurity.springSecurity.repo.CustomerRepository;
import com.example.springSecurity.springSecurity.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    private final ModelMapper modelMapper;


    @Override
    public String addCustomers(CustomerRequestDto requestDto) {
        Customers customers = modelMapper.map(requestDto, Customers.class);
        if(!customerRepository.existsByEmail(customers.getEmail())){
           customerRepository.save(customers);
           return "SUCCESS";
        }
        return "FAILED";
    }

    @Override
    public List<CustomerResponseDto> getAllCustomers(int page, int size) {
        Page<Customers> customerPage = customerRepository.findAll(PageRequest.of(page, size));

        List<CustomerResponseDto> customerDtos = customerPage.getContent().stream()
                .map(customer -> modelMapper.map(customer, CustomerResponseDto.class))
                .toList();

        return customerDtos;
    }
}
