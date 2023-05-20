package com.example.carrental.dto;

import com.example.carrental.model.Address;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class RentalDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;
    private String name;
    private String internetDomain;
    private Address address;
    private String owner;
    private String logoType;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<BranchDto> branches;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<CustomerDto> customers;
}
