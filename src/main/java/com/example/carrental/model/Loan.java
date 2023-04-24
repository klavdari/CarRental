package com.example.carrental.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Loan {
    private int id;
    private Employee employee;
    private Reservation reservation;
    private String comments;
}
