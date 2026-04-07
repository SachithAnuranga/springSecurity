package com.example.springSecurity.springSecurity.pagination;

import com.example.springSecurity.springSecurity.entity.Users;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * This DTO is used to send paginated user data
 * in API responses.
 *
 * It contains a list of Users for the current page
 * and a count of total records in the page.
 */
@Setter
@Getter
@Data
public class PaginatedResponseUserDto {
private List<Users> userDetailList;
private int dataCount;
}
