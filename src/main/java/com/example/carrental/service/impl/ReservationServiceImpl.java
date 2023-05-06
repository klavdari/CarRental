package com.example.carrental.service.impl;

import com.example.carrental.model.Reservation;
import com.example.carrental.repository.ReservationRepository;
import com.example.carrental.service.ReservationService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReservationServiceImpl implements ReservationService {

    private ReservationRepository reservationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Reservation createNewReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation getReservation(int id) {
        return reservationRepository.findById(id).orElse(null);
    }

    @Override
    public Reservation updateReservation(Reservation newReservation, int id) {
        Reservation reservation = reservationRepository.findById(id).orElse(null);

        reservation.setCar(newReservation.getCar());
        reservation.setBranchOfLoan(newReservation.getBranchOfLoan());
        reservation.setBranchOfReturn(newReservation.getBranchOfReturn());
        reservation.setDateFrom(newReservation.getDateFrom());
        reservation.setDateTo(newReservation.getDateTo());


        return reservationRepository.save(reservation);
    }

    @Override
    public void cancelReservation(int id) {
        reservationRepository.deleteById(id);
    }
}
