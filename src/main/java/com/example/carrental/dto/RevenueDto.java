package com.example.carrental.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RevenueDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;

    private double totalRevenue;

    private double cashback;

    private LocalDate date;
}
