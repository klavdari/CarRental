package com.example.carrental.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefundDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;

    private Integer employeeId;

    private Integer reservationId;

    private double surcharge;

    private String comments;

}
