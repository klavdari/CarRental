package com.example.carrental.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDate;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DateFormatException extends RuntimeException{
    LocalDate startDate;
    LocalDate endDate;

    public DateFormatException(LocalDate startDate, LocalDate endDate) {
        super(startDate + " cannot be after " + endDate);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
