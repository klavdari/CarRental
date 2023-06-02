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
public class ReservationDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDate dateOfBooking;

    private LocalDate dateFrom;

    private LocalDate dateTo;

    private int carId;

    private int branchOfLoanId;

    private int branchOfReturnId;

    private int customerId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private double totalAmount;



}
