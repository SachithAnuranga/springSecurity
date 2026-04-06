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

@RestController
@RequestMapping("customers/api/v1")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/save")
    public ResponseEntity<StandardResponse> addCustomers(@RequestBody CustomerRequestDto requestDto){
        String result = customerService.addCustomers(requestDto);
       return new ResponseEntity<StandardResponse>(new StandardResponse(200, result,
               "Success"), HttpStatus.OK);
    }

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
