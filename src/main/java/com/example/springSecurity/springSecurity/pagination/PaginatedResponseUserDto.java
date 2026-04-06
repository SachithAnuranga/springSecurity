package com.example.springSecurity.springSecurity.pagination;

import com.example.springSecurity.springSecurity.entity.Users;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Data
public class PaginatedResponseUserDto {
private List<Users> userDetailList;
private int dataCount;
}
