package com.example.carrental.repository;

import com.example.carrental.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarRepository extends JpaRepository<Car,Integer> {

    @Query(value = "select c from Car c where c.branchLocated.id = ?1")
    List<Car> findCarsByBranchId(int branchId);
}
