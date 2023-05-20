package com.example.carrental.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.time.LocalDate;
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

    @CreationTimestamp
    @Column(name = "date_of_booking")
    @Temporal(TemporalType.TIMESTAMP)
    private Instant dateOfBooking;

    @ManyToOne
    @JoinColumn(name = "customer")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "car")
    private Car car;

    @Temporal(TemporalType.DATE)
    @Column(name = "from_date")
    private LocalDate dateFrom;

    @Temporal(TemporalType.DATE)
    @Column(name = "to_date")
    private LocalDate dateTo;

    @ManyToOne
    @JoinColumn(name = "branch_of_loan")
    private Branch branchOfLoan;

    @ManyToOne
    @JoinColumn(name = "branch_of_return")
    private Branch branchOfReturn;

    @Column(name = "total_amount")
    private double totalAmount;
}
