package com.example.carrental.service.impl;

import com.example.carrental.dto.CarDto;
import com.example.carrental.dto.ReservationDto;
import com.example.carrental.exception.ResourceNotFoundException;
import com.example.carrental.model.Reservation;
import com.example.carrental.model.Status;
import com.example.carrental.repository.CarRepository;
import com.example.carrental.repository.ReservationRepository;
import com.example.carrental.service.CarService;
import com.example.carrental.service.ReservationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
@Service
public class ReservationServiceImpl implements ReservationService {

    private ReservationRepository reservationRepository;
    private ModelMapper modelMapper;
    private CarService carService;


    public ReservationServiceImpl(ReservationRepository reservationRepository,
                                  ModelMapper modelMapper,
                                  CarService carService) {
        this.reservationRepository = reservationRepository;
        this.modelMapper = modelMapper;
        this.carService = carService;

    }

    @Override
    public ReservationDto createNewReservation(ReservationDto reservationDto) {

        CarDto carDto = carService.getCar(reservationDto.getCarId());


        if (carDto.getBranchLocatedId() != reservationDto.getBranchOfLoanId()) {
            throw new RuntimeException("Car not found in selected branch " + reservationDto.getBranchOfLoanId());
        }
        else if(reservationRepository.isCarBooked(carDto.getId(),
                reservationDto.getDateFrom(),
                reservationDto.getDateTo())){

            throw new RuntimeException("Car is booked");
        } else {
            carDto.setStatus(Status.BOOKED);
            carDto.setBranchLocatedId(reservationDto.getBranchOfReturnId());
            carService.update(carDto,reservationDto.getCarId());
        }

        Reservation reservation = reservationRepository.save(modelMapper.map(reservationDto,Reservation.class));

        return modelMapper.map(reservation,ReservationDto.class);
    }

    @Override
    public List<ReservationDto> getAllReservations() {

        return Arrays.asList(modelMapper.map(reservationRepository.findAll(),ReservationDto[].class));
    }

    @Override
    public ReservationDto getReservation(int id) {
        Reservation reservation =  reservationRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Reservation not found with id ", "id",id));

        return modelMapper.map(reservation,ReservationDto.class);
    }

    @Override
    public ReservationDto updateReservation(ReservationDto reservationDto, int id) {

        ReservationDto newReservation = getReservation(id);

        newReservation.setCarId(reservationDto.getCarId());
        newReservation.setBranchOfLoanId(reservationDto.getBranchOfLoanId());
        newReservation.setBranchOfReturnId(reservationDto.getBranchOfReturnId());
        newReservation.setDateFrom(reservationDto.getDateFrom());
        newReservation.setDateTo(reservationDto.getDateTo());

        Reservation reservation = modelMapper.map(newReservation,Reservation.class);
        reservationRepository.save(reservation);

        return modelMapper.map(reservation,ReservationDto.class);
    }

    @Override
    public void cancelReservation(int id) {
        reservationRepository.deleteById(id);
    }
}
