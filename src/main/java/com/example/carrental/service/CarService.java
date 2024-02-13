package com.example.carrental.service;

import com.example.carrental.dto.CarDto;
import com.example.carrental.model.Status;

import java.time.LocalDate;
import java.util.List;

public interface CarService {

    CarDto addNewCar(CarDto carDto);

    List<CarDto> getCars();

    CarDto getCar(int id);

    CarDto update(CarDto carDto,int id);

    void delete(int id);

    List<CarDto> findAllBookedCars();

    List<CarDto> findAllAvailableCars(LocalDate startDate, LocalDate endDate,
                                      String color,String brand,String body,String model);

    List<CarDto> findCarsByBrand(String brand);

    List<CarDto> findCarsByColor(String color);

    List<CarDto> findCarsByBody(String body);

    List<CarDto> findCarsLessThan(Double amount);

    List<CarDto> findCarsGreaterThan(Double amount);

    List<CarDto> findCarsByYearAfter(Integer year);

    List<CarDto> findCarsByYearBefore(Integer year);

    List<CarDto> findCarsByBranchLocated(Integer branch);

    CarDto updateCarMileage(Integer carId,Integer mileage);

    CarDto updateCarStatus(Integer carId, Status status);

    CarDto updateCarPrice(Integer carId, Double amount);

}
