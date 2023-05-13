package com.example.carrental.service.impl;

import com.example.carrental.exception.ResourceNotFoundException;
import com.example.carrental.repository.RentalRepository;
import com.example.carrental.model.Rental;
import com.example.carrental.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RentalServiceImpl implements RentalService {

    private RentalRepository rentalRepository;


    @Autowired
    public RentalServiceImpl(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    @Override
    public Rental createNewRental(Rental rental) {
        return  rentalRepository.save(rental);

    }
    @Override
    public Rental getRental(int id) {
        return rentalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Rental","id",id));

    }

    @Override
    public List<Rental> listAllRentals() {
        return rentalRepository.findAll();
    }

    @Override
    public void deleteRentalById(int id) {
        rentalRepository.deleteById(id);
    }

    @Override
    public Rental configureRental(Rental newRental, int id) {

        Rental rental = rentalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Rental","id",id));

        rental.setName(newRental.getName());
        rental.setOwner(newRental.getOwner());
        rental.setAddress(newRental.getAddress());
        rental.setInternetDomain(newRental.getInternetDomain());
        rental.setLogoType(newRental.getLogoType());

        return rentalRepository.save(rental);
    }


}
