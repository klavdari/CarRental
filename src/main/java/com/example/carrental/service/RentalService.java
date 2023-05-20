package com.example.carrental.service;

import com.example.carrental.dto.RentalDto;
import com.example.carrental.model.Rental;

import java.util.List;

public interface RentalService {
    RentalDto createNewRental(RentalDto rentalDto);

    RentalDto configureRental(RentalDto rentalDto, int id);

    RentalDto getRental(int id);

    List<RentalDto> listAllRentals();

    void deleteRentalById(int id);

    
}
