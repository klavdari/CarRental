package com.example.carrental.repository;

import com.example.carrental.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

    List<Car> findAllByStatus(Status status);

    @Query(value = "SELECT * FROM Car "
            + "WHERE car_id NOT IN ("
            + "SELECT car_id FROM Reservation "
            + "WHERE :startDate <= to_date "
            + "AND :endDate >= from_date)"
            + "AND (:color IS NULL OR color = :color) "
            + "AND (:brand IS NULL OR brand = :brand) "
            + "AND (:body IS NULL OR body_type = :body) "
            + "AND (:model IS NULL OR model = :model) "
            + "AND (status IS NULL OR status <> 'UNAVAILABLE')", nativeQuery = true)
    List<Car> findAvailableCarsInGivenPeriod(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("color") String color,
            @Param("brand") String brand,
            @Param("body") String body,
            @Param("model") String model);

    List<Car> findCarsByBrand(String brand);

    List<Car> findCarsByColor(String color);

    List<Car> findCarsByBodyType(Body body);

    List<Car> findCarsByAmountPerDayIsLessThanEqualOrderByAmountPerDay(Double amount);

    List<Car> findCarsByAmountPerDayIsGreaterThanEqualOrderByAmountPerDay(Double amount);

    List<Car> findCarsByYearIsGreaterThanEqualOrderByYear(Integer year);

    List<Car> findCarsByYearIsLessThanEqualOrderByAmountPerDay(Integer year);

    List<Car> findCarsByBranchLocated(Branch branch);

}
