package com.example.carrental.service;

import com.example.carrental.model.Car;

import java.util.List;

public interface CarService {

    Car addNewCar(Car car,int branchId);

    List<Car> getCars(int branchId);

    Car getCar(int id,int branchId);

    Car update(Car car,int id,int branchId);

    void delete(int id,int branchId);
}
