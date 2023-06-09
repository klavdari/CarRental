package com.example.carrental.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private int id;

    @Column(name = "first_name")
    @NotEmpty(message = "First name cannot be empty")
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty(message = "Last name cannot be empty")
    private String lastName;

    @Column(name = "email")
    @Email
    private String email;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street",column = @Column(name = "customer_address_street")),
            @AttributeOverride(name = "city",column = @Column(name = "customer_address_city")),
            @AttributeOverride(name = "zipCode",column = @Column(name = "customer_address_zip"))
    })
    private Address address;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @OneToMany(mappedBy = "customer")
    private List<Reservation> reservations;


}
