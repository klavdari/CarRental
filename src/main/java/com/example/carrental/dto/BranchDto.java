package com.example.carrental.dto;
import com.example.carrental.model.Address;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
@Data
public class BranchDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;

    private Address address;


    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<EmployeeDto> employees;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<CarDto> cars;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int rentalId;
}
