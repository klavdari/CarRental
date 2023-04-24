package com.example.carrental.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    private String brand;
    private String model;
    private String bodyType;
    private String color;
    private int year;
    private int mileage;
    private Status status;
    private double amountPerDay;


}
