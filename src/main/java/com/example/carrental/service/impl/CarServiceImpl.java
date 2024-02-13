package com.example.carrental.service.impl;

import com.example.carrental.dto.CarDto;
import com.example.carrental.exception.DateFormatException;
import com.example.carrental.exception.ResourceNotFoundException;
import com.example.carrental.model.*;
import com.example.carrental.repository.BranchRepository;
import com.example.carrental.repository.CarRepository;
import com.example.carrental.repository.ReservationRepository;
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
    private ReservationRepository reservationRepository;
    private ModelMapper modelMapper;

    public CarServiceImpl(CarRepository carRepository, BranchRepository branchRepository,
                          ModelMapper modelMapper,
                          ReservationRepository reservationRepository) {
        this.carRepository = carRepository;
        this.branchRepository = branchRepository;
        this.modelMapper = modelMapper;
        this.reservationRepository = reservationRepository;
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
    public List<CarDto> findAllAvailableCars(LocalDate startDate,
                                             LocalDate endDate,String color,String brand,String body,String model) {
        if (ReservationServiceImpl.dateCheck(startDate, endDate)) {

            List<CarDto> cars = Arrays.asList(modelMapper
                    .map(carRepository
                            .findAvailableCarsInGivenPeriod(startDate,endDate,
                                     color,brand,body,model)
                            , CarDto[].class));

            for (CarDto car : cars) {

                car.setStatus(Status.AVAILABLE);
            }
            return cars;
        } else throw new DateFormatException(endDate, startDate);

    }

    @Override
    public List<CarDto> findCarsByBrand(String brand) {
        List<Car> cars = carRepository.findCarsByBrand(brand);
        return Arrays.asList(modelMapper.map(cars, CarDto[].class));
    }

    @Override
    public List<CarDto> findCarsByColor(String color) {
        List<Car> cars = carRepository.findCarsByColor(color);
        return Arrays.asList(modelMapper.map(cars,CarDto[].class));
    }

    @Override
    public List<CarDto> findCarsByBody(String body) {
        List<Car> cars = carRepository.findCarsByBodyType(Enum.valueOf(Body.class,body.toUpperCase()));
        return Arrays.asList(modelMapper.map(cars,CarDto[].class));
    }

    @Override
    public List<CarDto> findCarsLessThan(Double amount) {
        List<Car> cars = carRepository.findCarsByAmountPerDayIsLessThanEqualOrderByAmountPerDay(amount);
        return Arrays.asList(modelMapper.map(cars,CarDto[].class));
    }

    @Override
    public List<CarDto> findCarsGreaterThan(Double amount) {
        List<Car> cars = carRepository.findCarsByAmountPerDayIsGreaterThanEqualOrderByAmountPerDay(amount);
        return Arrays.asList(modelMapper.map(cars,CarDto[].class));
    }

    @Override
    public List<CarDto> findCarsByYearAfter(Integer year){
        List<Car> cars = carRepository.findCarsByYearIsGreaterThanEqualOrderByYear(year);
        return Arrays.asList(modelMapper.map(cars,CarDto[].class));
     }

     @Override
     public List<CarDto> findCarsByYearBefore(Integer year){
        List<Car> cars = carRepository.findCarsByYearIsLessThanEqualOrderByAmountPerDay(year);
        return Arrays.asList(modelMapper.map(cars,CarDto[].class));
     }

    @Override
    public List<CarDto> findCarsByBranchLocated(Integer branch) {
        List<Car>  cars = carRepository.findCarsByBranchLocated(branchRepository.findById(branch).orElseThrow());
        return Arrays.asList(modelMapper.map(cars,CarDto[].class));
    }

    @Override
    public CarDto updateCarMileage(Integer carId, Integer mileage) {
        Car car = carRepository.findById(carId).orElse(null);
        car.setMileage(mileage);
        carRepository.save(car);
        return modelMapper.map(car,CarDto.class);
    }

    @Override
    public CarDto updateCarStatus(Integer carId, Status status) {
        Car car = carRepository.findById(carId).orElse(null);
        car.setStatus(status);
        carRepository.save(car);
        return modelMapper.map(car,CarDto.class);
    }

    @Override
    public CarDto updateCarPrice(Integer carId, Double amount) {
        Car car = carRepository.findById(carId).orElse(null);
        car.setAmountPerDay(amount);
        carRepository.save(car);
        return modelMapper.map(car,CarDto.class);
    }

    //@Scheduled(fixedRate = 1000) // Run every day at midnight
    private void updateCarStatusBasedOnReservationsAutomatically() {
        List<Car> cars = carRepository.findAll();

        for (Car car : cars) {
            updateCarStatusBasedOnReservations(car.getId());
        }
    }


    private void updateCarStatusBasedOnReservations(int carId) {
        Car car = carRepository.findById(carId).orElse(null);

        if (car != null) {
            LocalDate currentDate = LocalDate.now();
            boolean isCarBooked = reservationRepository.isCarBookedAtCurrentDate(car, currentDate);

            if (isCarBooked) {
                // Car is booked
                car.setStatus(Status.BOOKED);
            } else if(car.getStatus() == Status.UNAVAILABLE) {
                car.setStatus(Status.UNAVAILABLE);
            }else{
                // Car is available
                car.setStatus(Status.AVAILABLE);
            }

            carRepository.save(car);
        }
    }
}

