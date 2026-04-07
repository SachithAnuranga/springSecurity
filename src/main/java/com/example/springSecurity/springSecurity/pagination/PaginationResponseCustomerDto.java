package com.example.springSecurity.springSecurity.pagination;

import com.example.springSecurity.springSecurity.dto.CustomerResponseDto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * This DTO is used to send paginated customer data
 * in API responses.
 *
 * It contains a list of CustomerResponseDto for the current page
 * and a count of records included in the response.
 */
@Data
@Getter
@Setter
public class PaginationResponseCustomerDto {
    private List<CustomerResponseDto> customerResponseDtoList;
    private int dataCount;
}
