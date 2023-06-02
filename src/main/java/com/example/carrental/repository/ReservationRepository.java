package com.example.carrental.repository;

import com.example.carrental.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,Integer> {

    @Query( "SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END "
            + "FROM Reservation r "
            + "WHERE r.car.id = :carId "
            + "AND ((r.dateFrom <= :startDate AND r.dateTo >= :endDate) "
            + "OR (r.dateFrom <= :startDate AND r.dateTo >= :endDate) "
            + "OR (r.dateFrom >= :startDate AND r.dateTo <= :endDate))")
    boolean isCarBooked(int carId,LocalDate startDate,LocalDate endDate);


    List<Reservation> findReservationsByCarId(int id);

}
