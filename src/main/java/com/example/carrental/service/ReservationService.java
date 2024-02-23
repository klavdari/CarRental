package com.example.carrental.service;

import com.example.carrental.dto.ReservationDto;
import com.example.carrental.model.Reservation;
import com.example.carrental.model.ReservationStatus;

import java.util.List;

public interface ReservationService {
    ReservationDto createNewReservation(ReservationDto reservationDto);

    List<ReservationDto> getAllReservations();

    ReservationDto getReservation(int id);

    void cancelReservation(int id);

    List<ReservationDto> getReservationByCar(int id);

    void updateReservationStatus(int id, ReservationStatus reservationStatus);
}
