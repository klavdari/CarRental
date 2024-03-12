package com.example.carrental.dto;

import com.example.carrental.model.ReservationStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ReservationDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDate dateOfBooking;

    private LocalDate dateFrom;

    private LocalDate dateTo;

    private Integer carId;

    private int branchOfLoanId;

    private int branchOfReturnId;

    private int customerId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private double totalAmount;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private ReservationStatus reservationStatus;


}
