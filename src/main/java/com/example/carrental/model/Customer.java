package com.example.carrental.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street",column = @Column(name = "customer_address_street")),
            @AttributeOverride(name = "city",column = @Column(name = "customer_address_city")),
            @AttributeOverride(name = "zipCode",column = @Column(name = "customer_address_zip"))
    })
    private Address address;


}
