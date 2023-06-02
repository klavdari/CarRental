package com.example.carrental.dto;

import com.example.carrental.model.Position;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class EmployeeDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;

    @NotEmpty(message = "First name cannot be empty")
    private String firstName;

    @NotEmpty(message = "First name cannot be empty")
    private String lastName;

    private Position position;

    private int workingBranchId;
}
