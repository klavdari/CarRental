package com.example.carrental.service.impl;

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
        return rentalRepository.findById(id).orElse(null);

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

        Rental rental = rentalRepository.findById(id).orElse(null);

        rental.setName(newRental.getName());
        rental.setOwner(newRental.getOwner());
        rental.setAddress(newRental.getAddress());
        rental.setInternetDomain(newRental.getInternetDomain());
        rental.setLogoType(newRental.getLogoType());

        return rentalRepository.save(rental);
    }


//
//    // convert Entity into DTO
//    private RentalDto mapToDTO(Rental rental){
//
//        RentalDto rentalDto = new RentalDto();
//        rentalDto.setId(rental.getId());
//        rentalDto.setName(rental.getName());
//        rentalDto.setOwner(rental.getOwner());
//        rentalDto.setAddress(rental.getAddress());
//        rentalDto.setLogoType(rental.getLogoType());
//        return rentalDto;
//    }
//
//    // convert DTO to entity
//    private Rental mapToEntity(RentalDto rentalDto){
//
//        Rental rental = new Rental();
//        rental.setName(rentalDto.getName());
//        rental.setOwner(rentalDto.getOwner());
//        rental.setAddress(rentalDto.getAddress());
//        rental.setLogoType(rentalDto.getLogoType());
//        rental.setInternetDomain(rentalDto.getInternetDomain());
//        return rental;
//    }


}
