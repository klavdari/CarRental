package com.example.carrental.dto;

import com.example.carrental.model.Body;
import com.example.carrental.model.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CarDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;

    @NotEmpty(message = "Brand cannot be empty")
    private String brand;

    @NotEmpty(message = "Model cannot be empty")
    private String model;


    private Body bodyType;

    @NotEmpty(message = "Color cannot be empty")
    private String color;


    @Min(value = 1950,message = "Year cannot be lower than 1950")
    @NotNull(message = "Brand cannot be empty")
    private int year;

    @Min(value =0,message = "mileage cannot be lower than 0 km")
    private int mileage;

    @Min(value = 1,message = "amount cannot be lower than 0 ")
    private double amountPerDay;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Status status = Status.AVAILABLE;


    private int branchLocatedId;
}
