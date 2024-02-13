package com.example.carrental.controller;

import com.example.carrental.dto.CarDto;
import com.example.carrental.model.Body;
import com.example.carrental.model.Status;
import com.example.carrental.service.CarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    @GetMapping("/booked")
    public List<CarDto> getBookedCars(){
        return carService.findAllBookedCars();
    }

    @PatchMapping("/{carId}/mileage")
    public CarDto updateCarMileage(@PathVariable Integer carId,@RequestParam Integer mileage){
        return carService.updateCarMileage(carId,mileage);
    }

    @PatchMapping("{carId}/status")
    public CarDto updateCarStatus(@PathVariable Integer carId, @RequestParam Status status){
        return carService.updateCarStatus(carId,status);
    }

    @PatchMapping("{carId}/price")
    public CarDto updateCarPrice(@PathVariable Integer carId, @RequestParam Double amount){
        return carService.updateCarPrice(carId,amount);
    }

    @GetMapping("/available")
    public List<CarDto> getAvailableCars(
            @RequestParam LocalDate start,LocalDate end,
            @RequestParam(name = "color", required = false) String color,
            @RequestParam(name = "brand", required = false) String brand,
            @RequestParam(name = "body", required = false)String body,
            @RequestParam(name = "model", required = false) String model){
        return carService.findAllAvailableCars(start,end,color,brand,body,model);
    }

    @GetMapping("/color")
    public List<CarDto> getCarsByColor(@RequestParam String color){
        return carService.findCarsByColor(color);
    }

    @GetMapping("/body")
    public List<CarDto> getCarsByBodyType(@RequestParam String body){
        return carService.findCarsByBody(body);
    }

    @GetMapping("/greater")
    public List<CarDto> getCarsGreaterThan(@RequestParam Double amount){
        return carService.findCarsGreaterThan(amount);
    }

    @GetMapping("/less")
    public List<CarDto> getCarsLessThan(@RequestParam Double amount){
        return carService.findCarsLessThan(amount);
    }

    @GetMapping("/after")
    public List<CarDto> getCarsYearAfter(@RequestParam Integer year){
        return carService.findCarsByYearAfter(year);
    }

    @GetMapping("/before")
    public List<CarDto> getCarsYearBefore(@RequestParam Integer year){
        return carService.findCarsByYearBefore(year);
    }

    @GetMapping("/brand")
    public List<CarDto> getCarsByModel(@RequestParam String brand){
        return carService.findCarsByBrand(brand);
    }

    @GetMapping("/branch")
    public List<CarDto> getCarsByBranch(@RequestParam Integer branch){
        return carService.findCarsByBranchLocated(branch);
    }
}
