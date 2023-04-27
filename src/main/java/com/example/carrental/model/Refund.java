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
    private int id;
    @ManyToOne
    private Employee employee;
    @OneToOne
    private Reservation reservation;
    @Column(name = "surcharge")
    private double surcharge;
    @Column(name = "comments")
    private String comments;
}
