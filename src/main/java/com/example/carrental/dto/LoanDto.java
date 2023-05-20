package com.example.carrental.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@Data
public class LoanDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;

    private int employeeId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int reservationId;

    private String comments;

    private LocalDate reservationDateFrom;

    private LocalDate reservationDateTo;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Temporal(TemporalType.TIMESTAMP)
    private final Instant reservationDateOfBooking = Instant.now();

    private int reservationCarId;

    private int reservationBranchOfLoanId;

    private int reservationBranchOfReturnId;

}
