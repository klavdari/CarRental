package com.example.carrental.model;

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
    private int id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street",column = @Column(name = "branch_street")),
            @AttributeOverride(name = "city",column = @Column(name = "branch_city")),
            @AttributeOverride(name = "zipCode",column = @Column(name = "branch_zip"))
    })
    private Address address;
    @OneToMany(mappedBy = "workingBranch")
    private List<Employee> employees;
    @OneToMany(mappedBy = "branchLocated")
    private List<Car> availableCars;

}
