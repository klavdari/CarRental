package com.example.carrental.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

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

    @Column(name = "total_revenue")
    private double totalRevenue;

    @Column(name = "cashback")
    private double cashback;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private LocalDate date;
}
