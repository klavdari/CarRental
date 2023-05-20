package com.example.carrental.controller;

import com.example.carrental.dto.RentalDto;
import com.example.carrental.model.Rental;
import com.example.carrental.service.RentalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rental")
public class RentalController {

    private RentalService rentalService;

    @Autowired
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping()
    public ResponseEntity<RentalDto> addRental(@Valid @RequestBody RentalDto rentalDto){

        return new ResponseEntity<>(rentalService.createNewRental(rentalDto),HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<RentalDto>> getAllRentals(){

        return new ResponseEntity<>(rentalService.listAllRentals(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public RentalDto getRentalById(@PathVariable("id") int id){
       return rentalService.getRental(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteRentalById(@PathVariable("id") int id){
        rentalService.deleteRentalById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RentalDto> updateRental(@Valid @RequestBody RentalDto rentalDto,@PathVariable int id){
        return new ResponseEntity<>(rentalService.configureRental(rentalDto,id),HttpStatus.ACCEPTED);
    }

}
