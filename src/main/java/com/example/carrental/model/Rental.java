package com.example.carrental.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rental")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rental_id")
    private int id;

    @NotEmpty
    @Size(min = 2, message = "Name should have at least 2 characters")
    @Column(name = "name")
    private String name;

    @Column(name = "internet_domain")
    private String internetDomain;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street",column = @Column(name = "rental_street")),
            @AttributeOverride(name = "city",column = @Column(name = "rental_city")),
            @AttributeOverride(name = "zipCode",column = @Column(name = "rental_zip"))
    })
    @Valid
    private Address address;

    @NotEmpty
    @Size(min = 2, message = "Owner name should have at least 2 characters")
    @Column(name = "owner")
    private String owner;

    @Column(name = "logo_type")
    private String logoType;


    @OneToMany(mappedBy = "rental")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Branch> branches;

    @ManyToMany
    @JoinTable(
            name = "rental_customers",
            joinColumns = @JoinColumn(
                    name = "rental_id",referencedColumnName = "rental_id"),
            inverseJoinColumns = @JoinColumn(
                    name = "customer_id",referencedColumnName = "customer_id"
            )
    )
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Customer> customers;
}
