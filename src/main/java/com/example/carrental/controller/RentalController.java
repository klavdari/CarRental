package com.example.carrental.controller;

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

    @Autowired
    private RentalService rentalService;

    @PostMapping()
    public ResponseEntity<Rental> addRental(@Valid @RequestBody Rental rental){

        Rental newRental= rentalService.createNewRental(rental);

        return new ResponseEntity<>(newRental, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Rental>> getRental(){

        List<Rental> rentals = rentalService.listAllRentals();

        return new ResponseEntity<>(rentals,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Rental getRentalById(@PathVariable("id") int id){
       return rentalService.getRental(id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteRentalById(@PathVariable("id") int id){
        rentalService.deleteRentalById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public Rental updateRental(@Valid @RequestBody Rental rental,@PathVariable int id){
        return rentalService.configureRental(rental,id);
    }

}
