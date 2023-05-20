package com.example.carrental.dto;

import com.example.carrental.model.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
@Data
public class ReservationDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Temporal(TemporalType.TIMESTAMP)
    private final Instant dateOfBooking = Instant.now();

    private LocalDate dateFrom;

    private LocalDate dateTo;

    private int carId;

    private Status carStatus;

    private int branchOfLoanId;

    private int branchOfReturnId;

    private int customerId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private double totalAmount;



}
