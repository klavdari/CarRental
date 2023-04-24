package com.example.carrental.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rental {

    private String name;
    private String internetDomain;
    private String contactAddress;
    private String owner;
    private String logoType;
    private List<Branch> branches;
}
