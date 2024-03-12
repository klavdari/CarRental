package com.example.carrental.dto;

import com.example.carrental.model.ReservationStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@Data
@ToString
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
    @CreatedDate
    private LocalDate reservationDateOfBooking = LocalDate.now();

    private int reservationCarId;

    private int reservationBranchOfLoanId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private ReservationStatus reservationStatus;

    private int reservationBranchOfReturnId;

}
