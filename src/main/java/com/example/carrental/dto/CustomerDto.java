package com.example.carrental.dto;

import com.example.carrental.model.Address;
import lombok.Data;

@Data
public class CustomerDto {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private Address address;
}
