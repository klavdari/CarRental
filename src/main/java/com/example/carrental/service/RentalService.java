package com.example.carrental.service;

import com.example.carrental.model.Rental;

import java.util.List;

public interface RentalService {
    Rental createNewRental(Rental rental);

    Rental configureRental(Rental rental, int id);

    Rental getRental(int id);

    List<Rental> listAllRentals();

    void deleteRentalById(int id);

    
}
