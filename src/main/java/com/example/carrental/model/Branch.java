package com.example.carrental.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "branch")
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "branch_id")
    private Integer id;

    @Embedded
    @AttributeOverride(name = "street",column = @Column(name = "branch_street"))
    @AttributeOverride(name = "city",column = @Column(name = "branch_city"))
    @AttributeOverride(name = "zipCode",column = @Column(name = "branch_zip"))
    private Address address;

    @OneToMany(mappedBy = "workingBranch")
    private List<Employee> employees;

    @OneToMany(mappedBy = "branchLocated")
    private List<Car> availableCars;

    @ManyToOne
    @JoinColumn(name = "rental_located")
    private Rental rental;

    @Column(name = "active_branch")
    @JsonIgnore
    private boolean active;

}
