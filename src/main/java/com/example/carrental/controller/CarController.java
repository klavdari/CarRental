package com.example.carrental.controller;

import com.example.carrental.model.Car;
import com.example.carrental.service.CarService;
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
    public ResponseEntity<Car> addNewCar(@RequestBody Car car,int branchId){
        Car newCar =  carService.addNewCar(car,branchId);

        return new ResponseEntity<>(newCar,HttpStatus.CREATED);
    }
    @GetMapping
    public List<Car> getCarsByBranch(@RequestBody int branchId){
        return carService.getCars(branchId);
    }
    @GetMapping("/{carId}")
    public Car getCar(@PathVariable(value = "carId") int carId , int branchId){
        return carService.getCar(carId,branchId);
    }
    @PutMapping("/{carId}")
    public Car updateCar(@RequestBody Car car,@PathVariable(value = "carId") int carId, int branchId){
        return carService.update(car,carId,branchId);
    }
    @DeleteMapping("/{carId}")
    public ResponseEntity<Car> removeCar(@PathVariable int carId,int branchId){
        carService.delete(carId,branchId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
