package com.example.carrental.controller;

import com.example.carrental.model.Reservation;
import com.example.carrental.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private ReservationService reservationService;

    public ReservationController(ReservationService reservationService){
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<Reservation> addReservation(@RequestBody Reservation reservation){
        return new ResponseEntity<>(reservationService.createNewReservation(reservation), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getReservations(){
        return new ResponseEntity<>(reservationService.getAllReservations(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservation(@PathVariable int id){
        return new ResponseEntity<>(reservationService.getReservation(id),HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Reservation> updateReservation(@RequestBody Reservation reservation,int id){
        return new ResponseEntity<>(reservationService.updateReservation(reservation,id),HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteReservation(int id){
        reservationService.cancelReservation(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
