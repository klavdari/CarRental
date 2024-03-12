package com.example.carrental.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "employee")
    private Employee employee;



    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "reservation")
    private Reservation reservation;

    @Column(name = "comments")
    private String comments;
}
