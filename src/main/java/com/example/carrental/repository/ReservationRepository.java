package com.example.carrental.repository;

import com.example.carrental.model.Car;
import com.example.carrental.model.Reservation;
import com.example.carrental.model.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,Integer> {

    @Query( "SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END "
            + "FROM Reservation r "
            + "WHERE r.car.id = :carId "
            + "AND ((r.dateFrom <= :startDate AND r.dateTo >= :endDate) "
            + "OR (r.dateFrom <= :startDate AND r.dateTo >= :endDate) "
            + "OR (r.dateFrom >= :startDate AND r.dateTo <= :endDate))" +
            "AND r.reservationStatus <> 'CANCELLED'")
    boolean isCarBooked(int carId,LocalDate startDate,LocalDate endDate);


    List<Reservation> findReservationsByCarId(int id);

    @Query("SELECT COUNT(r) > 0 FROM Reservation r " +
            "WHERE r.car = :car " +
            "AND :currentDate BETWEEN r.dateFrom AND r.dateTo")
    boolean isCarBookedAtCurrentDate(@Param("car") Car car,
                                     @Param("currentDate") LocalDate currentDate);

}
