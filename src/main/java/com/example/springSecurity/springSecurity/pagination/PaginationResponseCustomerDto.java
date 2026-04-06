package com.example.springSecurity.springSecurity.pagination;

import com.example.springSecurity.springSecurity.dto.CustomerResponseDto;
import com.example.springSecurity.springSecurity.entity.Customers;
import com.example.springSecurity.springSecurity.entity.Users;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class PaginationResponseCustomerDto {
    private List<CustomerResponseDto> customerResponseDtoList;
    private int dataCount;
}
