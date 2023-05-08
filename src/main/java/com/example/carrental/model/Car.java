package com.example.carrental.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
    private String brand;

    @Column(name = "model")
    private String model;

    @Enumerated(EnumType.STRING)
    @Column(name = "body_type")
    private Body bodyType;

    @Column(name = "color")
    private String color;

    @Column(name = "year")
    private int year;

    @Column(name = "mileage")
    private int mileage;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "amount_per_day")
    private double amountPerDay;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "branch_located")
    private Branch branchLocated;


}
