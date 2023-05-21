package com.example.carrental.repository;

import com.example.carrental.model.Car;
import com.example.carrental.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface CarRepository extends JpaRepository<Car,Integer> {

    List<Car> findAllByStatus(Status status);
}
