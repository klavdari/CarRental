package com.example.carrental.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Refund {
    private Employee employee;
    private Reservation reservation;
    private double surcharge;
    private String comments;
}
