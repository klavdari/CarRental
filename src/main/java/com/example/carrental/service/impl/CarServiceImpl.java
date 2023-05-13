package com.example.carrental.service.impl;

import com.example.carrental.exception.ResourceNotFoundException;
import com.example.carrental.model.Branch;
import com.example.carrental.model.Car;
import com.example.carrental.repository.BranchRepository;
import com.example.carrental.repository.CarRepository;
import com.example.carrental.service.CarService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {


    private CarRepository carRepository;
    private BranchRepository branchRepository;

    public CarServiceImpl(CarRepository carRepository,BranchRepository branchRepository) {
        this.carRepository = carRepository;
        this.branchRepository = branchRepository;
    }

    @Override
    public Car addNewCar(Car car,int branchId){
      Branch branch =  branchRepository.findById(branchId).orElseThrow(() -> new RuntimeException("Branch does not exist"));
        car.setBranchLocated(branch);
        return carRepository.save(car);
    }

    @Override
    public List<Car> getCars(int branchId) {
        return carRepository.findCarsByBranchId(branchId);
    }

    @Override
    public Car getCar(int id,int branchId) {

      return carRepository.findCarsByBranchId(branchId)
                .stream()
                .filter(c->c.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Car","id",id));
    }

    @Override
    public Car update(Car newCar, int id,int branchId) {

        Car car = getCar(id,branchId);

        car.setAmountPerDay(newCar.getAmountPerDay());
        car.setMileage(newCar.getMileage());
        car.setBranchLocated(car.getBranchLocated());

        return carRepository.save(car);
    }

    @Override
    public void delete(int id,int branchId) {
        Car car = getCar(id,branchId);
        carRepository.deleteById(car.getId());
    }
}
