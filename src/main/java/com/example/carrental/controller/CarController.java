package com.example.carrental.controller;

import com.example.carrental.dto.CarDto;
import com.example.carrental.model.Car;
import com.example.carrental.service.CarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    public ResponseEntity<CarDto> addNewCar(
            @Valid @RequestBody CarDto car){
    CarDto newCar = carService.addNewCar(car);
        return new ResponseEntity<>(newCar,HttpStatus.CREATED);
    }
    @GetMapping
    public List<CarDto> getCarsByBranch(){
        return carService.getCars();
    }

    @GetMapping("/{carId}")
    public CarDto getCar(@PathVariable(value = "carId") int carId){
        return carService.getCar(carId);
    }
    @PutMapping("/{carId}")
    public CarDto updateCar(@Valid @RequestBody CarDto car,@PathVariable(value = "carId") int carId){
        return carService.update(car,carId);
    }
    @DeleteMapping("/{carId}")
    public ResponseEntity<CarDto> removeCar(@PathVariable int carId){
        carService.delete(carId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
