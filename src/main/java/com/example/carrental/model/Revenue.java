package com.example.carrental.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Revenue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "revenue_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "reservation")
    private Reservation reservation;

    @Column(name = "total_revenue")
    private double totalRevenue;

    @Column(name = "cashback")
    private double cashback;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(name = "revenue_type")
    private RevenueType revenueType;
}
