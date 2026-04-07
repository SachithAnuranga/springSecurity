package com.example.springSecurity.springSecurity.controller;

import com.example.springSecurity.springSecurity.dto.CustomerRequestDto;
import com.example.springSecurity.springSecurity.dto.CustomerResponseDto;
import com.example.springSecurity.springSecurity.entity.Customers;
import com.example.springSecurity.springSecurity.pagination.PaginationResponseCustomerDto;
import com.example.springSecurity.springSecurity.response.StandardResponse;
import com.example.springSecurity.springSecurity.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This controller handles all customer-related API endpoints.
 *
 * It provides operations for:
 * - Creating new customers
 * - Retrieving customer data with pagination
 *
 * Base URL: /customers/api/v1
 */
@RestController
@RequestMapping("customers/api/v1")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * Handles HTTP POST request to create a new customer.
     *
     * Accepts customer data as a request body and delegates
     * the creation process to the service layer.
     *
     * @param requestDto Customer request data (DTO)
     * @return ResponseEntity containing standard response with status and message
     */
    @PostMapping("/save")
    public ResponseEntity<StandardResponse> addCustomers(@RequestBody CustomerRequestDto requestDto){
        String result = customerService.addCustomers(requestDto);
       return new ResponseEntity<StandardResponse>(new StandardResponse(200, result,
               "Success"), HttpStatus.OK);
    }

    /**
     * Handles HTTP GET request to retrieve all customers with pagination.
     *
     * Accepts page number and page size as request parameters
     * and returns a paginated list of customers.
     *
     * @param page page number
     * @param size number of records per page
     * @return ResponseEntity containing paginated customer data
     */
    @GetMapping("/list")
    public ResponseEntity<StandardResponse> getAllCustomers(@RequestParam int page, @RequestParam int size){
        List<CustomerResponseDto> result = customerService.getAllCustomers(page, size);
        PaginationResponseCustomerDto responseCustomerDto = new PaginationResponseCustomerDto();
        responseCustomerDto.setCustomerResponseDtoList(result);
        responseCustomerDto.setDataCount(result.size());

        return new ResponseEntity<StandardResponse>(new StandardResponse(200, responseCustomerDto,
                "Success"), HttpStatus.OK);
    }
}
