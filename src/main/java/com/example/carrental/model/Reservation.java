package com.example.carrental.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    private Date dateOfBooking;
    private Customer customer;
    private Car car;
    private Date dateFrom;
    private Date dateTo;
    private Branch branchOfLoan;
    private Branch branchOfReturn;
    private double totalAmount;
}
