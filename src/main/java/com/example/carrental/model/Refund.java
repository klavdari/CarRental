package com.example.carrental.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Refund {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "refund_id")
    private int id;


    @ManyToOne
    @JoinColumn(name = "employee")
    private Employee employee;

    @OneToOne
    @JoinColumn(name = "reservation")
    private Reservation reservation;

    @Column(name = "surcharge")
    private double surcharge;

    @Column(name = "comments")
    private String comments;
}
