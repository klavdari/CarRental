package com.example.carrental.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private int id;

    @Column(name = "brand")
    @NotEmpty(message = "Brand cannot be empty")
    private String brand;

    @Column(name = "model")
    @NotEmpty(message = "Model cannot be empty")
    private String model;

    @Enumerated(EnumType.STRING)
    @Column(name = "body_type")
    private Body bodyType;

    @Column(name = "color")
    @NotEmpty(message = "Color cannot be empty")
    private String color;

    @Column(name = "year")
    @Min(value = 1950,message = "Year cannot be lower than 1950")
    @NotNull(message = "Brand cannot be empty")
    private int year;

    @Column(name = "mileage")
    @NotNull
    private int mileage;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "amount_per_day")
    @NotNull
    private double amountPerDay;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "branch_located")
    private Branch branchLocated;


}
