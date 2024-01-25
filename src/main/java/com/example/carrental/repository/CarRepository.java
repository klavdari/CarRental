package com.example.carrental.repository;

import com.example.carrental.model.Car;
import com.example.carrental.model.Reservation;
import com.example.carrental.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface CarRepository extends JpaRepository<Car,Integer> {

    List<Car> findAllByStatus(Status status);

    @Scheduled(cron = "0 */2 * * * *")
    @Modifying
    @Query(value = "update car c set c.status = 'BOOKED' where car_id in " +
            "(select car from reservation r where current_date between r.from_date and r.to_date)",
            nativeQuery = true)
    void updateCarStatus();

    @Query(value = "SELECT * FROM Car  "
            + "WHERE car_id NOT IN ("
            + "SELECT car_id FROM Reservation  "
            + "WHERE :startDate <= to_date "
            + "AND :endDate >= from_date"
            + ")", nativeQuery = true)
    List<Car> findAvailableCarsInGivenPeriod(LocalDate startDate,LocalDate endDate);

}
