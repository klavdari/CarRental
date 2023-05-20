package com.example.carrental.service.impl;

import com.example.carrental.dto.RentalDto;
import com.example.carrental.exception.ResourceNotFoundException;
import com.example.carrental.repository.RentalRepository;
import com.example.carrental.model.Rental;
import com.example.carrental.service.RentalService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


@Service
public class RentalServiceImpl implements RentalService {

    private RentalRepository rentalRepository;

    private ModelMapper modelMapper;

    @Autowired
    public RentalServiceImpl(RentalRepository rentalRepository,ModelMapper modelMapper) {
        this.rentalRepository = rentalRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public RentalDto createNewRental(RentalDto rentalDto) {

        Rental rental = mapToEntity(rentalDto);
        Rental newRental = rentalRepository.save(rental);

        return  mapToDto(newRental);

    }
    @Override
    public RentalDto getRental(int id) {

        Rental rental = rentalRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Rental","id",id));
        return mapToDto(rental);

    }

    @Override
    public List<RentalDto> listAllRentals() {

        List<Rental> rentals = rentalRepository.findAll();

        return Arrays.asList(modelMapper.map(rentals,RentalDto[].class));
    }

    @Override
    public void deleteRentalById(int id) {
        rentalRepository.deleteById(id);
    }

    @Override
    public RentalDto configureRental(RentalDto newRentalDto, int id) {

        Rental rental = rentalRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Rental","id",id));

        rental.setName(newRentalDto.getName());
        rental.setOwner(newRentalDto.getOwner());
        rental.setAddress(newRentalDto.getAddress());
        rental.setInternetDomain(newRentalDto.getInternetDomain());
        rental.setLogoType(newRentalDto.getLogoType());

        Rental updatedRental = rentalRepository.save(rental);
        return mapToDto(updatedRental);
    }



    private RentalDto mapToDto(Rental rental){
        return modelMapper.map(rental,RentalDto.class);
    }

    private Rental mapToEntity(RentalDto rentalDto){
        return modelMapper.map(rentalDto,Rental.class);
    }
}
