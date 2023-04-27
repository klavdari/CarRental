package com.example.carrental.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "date_of_booking")
    private Date dateOfBooking;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Car car;

    @Column(name = "from_date")
    private Date dateFrom;

    @Column(name = "to_date")
    private Date dateTo;

    @ManyToOne
    private Branch branchOfLoan;

    @ManyToOne
    private Branch branchOfReturn;

    @Column(name = "total_amount")
    private double totalAmount;
}
