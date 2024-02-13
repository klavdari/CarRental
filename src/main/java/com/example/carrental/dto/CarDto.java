package com.example.carrental.dto;

import com.example.carrental.model.Body;
import com.example.carrental.model.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CarDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;

    private String brand;

    private String model;

    private Body bodyType;

    private String color;

    private int year;

    private int mileage;

    private double amountPerDay;

    private Status status = Status.AVAILABLE;

    private int branchLocatedId;
}
