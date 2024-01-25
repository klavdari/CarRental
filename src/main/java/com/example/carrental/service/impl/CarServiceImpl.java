package com.example.carrental.service.impl;

import com.example.carrental.dto.CarDto;
import com.example.carrental.exception.DateFormatException;
import com.example.carrental.exception.ResourceNotFoundException;
import com.example.carrental.model.Branch;
import com.example.carrental.model.Car;
import com.example.carrental.model.Status;
import com.example.carrental.repository.BranchRepository;
import com.example.carrental.repository.CarRepository;
import com.example.carrental.service.CarService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {


    private CarRepository carRepository;
    private BranchRepository branchRepository;
    private ModelMapper modelMapper;

    public CarServiceImpl(CarRepository carRepository, BranchRepository branchRepository, ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.branchRepository = branchRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CarDto addNewCar(CarDto carDto) {

        Branch branch = branchRepository.findById(carDto.getBranchLocatedId()).orElseThrow(() ->
                new RuntimeException("Branch does not exist"));
        Car car = modelMapper.map(carDto, Car.class);
        car.setBranchLocated(branch);
        Car newCar = carRepository.save(car);

        return modelMapper.map(newCar, CarDto.class);
    }

    @Override
    public List<CarDto> getCars() {

        List<Car> cars = carRepository.findAll();

        return Arrays.asList(modelMapper.map(cars, CarDto[].class));
    }

    @Override
    public CarDto getCar(int id) {

        Car car = carRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Car not found with id ", "id", id));
        return modelMapper.map(car, CarDto.class);
    }

    @Override
    public CarDto update(CarDto carDto, int id) {

        CarDto newCar = getCar(id);

        newCar.setAmountPerDay(carDto.getAmountPerDay());
        newCar.setMileage(carDto.getMileage());
        newCar.setBranchLocatedId(carDto.getBranchLocatedId());
        newCar.setStatus(carDto.getStatus());


        Car car = modelMapper.map(newCar, Car.class);

        carRepository.save(car);

        return modelMapper.map(car, CarDto.class);
    }

    @Override
    public void delete(int id) {
        carRepository.deleteById(id);
    }

    @Override
    public List<CarDto> findAllBookedCars() {
        List<Car> bookedCars = carRepository.findAllByStatus(Status.BOOKED);
        return Arrays.asList(modelMapper.map(bookedCars, CarDto[].class));
    }

    @Override
    public List<CarDto> findAllAvailableCars(LocalDate startDate, LocalDate endDate) {
        if (ReservationServiceImpl.dateCheck(startDate, endDate)) {

            List<CarDto> cars = Arrays.asList(modelMapper
                    .map(carRepository
                            .findAvailableCarsInGivenPeriod(startDate, endDate), CarDto[].class));

            for (CarDto car : cars) {
                car.setStatus(Status.AVAILABLE);
            }
            return cars;
        }else throw new DateFormatException(endDate,startDate);

    }
}
