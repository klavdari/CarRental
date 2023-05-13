package com.example.carrental.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @Column(name = "reservation_id")
    private int id;

    @Column(name = "date_of_booking")
    private Date dateOfBooking;

    @ManyToOne
    @JoinColumn(name = "customer")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "car")
    private Car car;

    @Column(name = "from_date")
    private Date dateFrom;

    @Column(name = "to_date")
    private Date dateTo;

    @ManyToOne
    @JoinColumn(name = "branch_of_loan")

    private Branch branchOfLoan;

    @ManyToOne
    @JoinColumn(name = "branch_of_return")
    private Branch branchOfReturn;

    @Column(name = "total_amount")
    private double totalAmount;
}
