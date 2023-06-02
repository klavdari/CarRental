package com.example.carrental.service;

import com.example.carrental.dto.CarDto;
import com.example.carrental.model.Car;
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

    List<CarDto> findAllAvailableCars(LocalDate startDate,LocalDate endDate);
}
