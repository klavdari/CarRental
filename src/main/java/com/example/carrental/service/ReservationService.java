package com.example.carrental.service;

import com.example.carrental.model.Reservation;

import java.util.List;

public interface ReservationService {
    Reservation createNewReservation(Reservation reservation);

    List<Reservation> getAllReservations();

    Reservation getReservation(int id);

    Reservation updateReservation(Reservation reservation,int id);

    void cancelReservation(int id);
}
