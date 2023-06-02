package com.example.carrental.service.impl;

import com.example.carrental.dto.CarDto;
import com.example.carrental.dto.ReservationDto;
import com.example.carrental.dto.RevenueDto;
import com.example.carrental.exception.DateFormatException;
import com.example.carrental.exception.ResourceNotFoundException;
import com.example.carrental.model.Reservation;
import com.example.carrental.model.Revenue;
import com.example.carrental.model.Status;
import com.example.carrental.repository.ReservationRepository;
import com.example.carrental.service.CarService;
import com.example.carrental.service.ReservationService;
import com.example.carrental.service.RevenueService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
@Service
public class ReservationServiceImpl implements ReservationService {

    private ReservationRepository reservationRepository;
    private ModelMapper modelMapper;
    private CarService carService;
    private RevenueService revenueService;


    public ReservationServiceImpl(ReservationRepository reservationRepository,
                                  ModelMapper modelMapper,
                                  CarService carService,
                                  RevenueService revenueService) {
        this.reservationRepository = reservationRepository;
        this.modelMapper = modelMapper;
        this.carService = carService;
        this.revenueService = revenueService;

    }

    @Override
    public ReservationDto createNewReservation(ReservationDto reservationDto) {

        CarDto carDto = carService.getCar(reservationDto.getCarId());

        reservationCheck(reservationDto, carDto);

        Reservation reservation = reservationRepository.save(modelMapper.map(reservationDto,Reservation.class));

        RevenueDto revenue = new RevenueDto();
        revenue.setTotalRevenue(reservationDto.getTotalAmount());
        revenue.setDate(reservationDto.getDateOfBooking());
        revenueService.create(revenue);

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


    //TODO updateReservation -> bug -> updated reservation cannot override current reservation
    @Override
    public ReservationDto updateReservation(ReservationDto reservationDto, int id) {

        ReservationDto newReservation = getReservation(id);

        newReservation.setCarId(reservationDto.getCarId());

        CarDto carDto = carService.getCar(reservationDto.getCarId());

        newReservation.setBranchOfLoanId(reservationDto.getBranchOfLoanId());
        newReservation.setBranchOfReturnId(reservationDto.getBranchOfReturnId());
        newReservation.setDateFrom(reservationDto.getDateFrom());
        newReservation.setDateTo(reservationDto.getDateTo());

        reservationCheck(reservationDto, carDto);

        Reservation reservation = modelMapper.map(newReservation,Reservation.class);
        reservationRepository.save(reservation);

        return modelMapper.map(reservation,ReservationDto.class);
    }

    private void reservationCheck(ReservationDto reservationDto, CarDto carDto) {
        if (carDto.getBranchLocatedId() != reservationDto.getBranchOfLoanId()) {
            throw new RuntimeException("Car not found in selected branch " + reservationDto.getBranchOfLoanId());

        } else if (!dateCheck(reservationDto.getDateFrom(),reservationDto.getDateTo())) {
            throw new DateFormatException(reservationDto.getDateFrom(),reservationDto.getDateTo());

        } else if(reservationRepository.isCarBooked(carDto.getId(),
                reservationDto.getDateFrom(),
                reservationDto.getDateTo())){

            throw new RuntimeException("Car is booked");
        } else {
            if(LocalDate.now().isEqual(reservationDto.getDateFrom())){
                carDto.setStatus(Status.BOOKED);
                reservationDto.setDateOfBooking(LocalDate.now());
                carDto.setBranchLocatedId(reservationDto.getBranchOfReturnId());
                carService.update(carDto,reservationDto.getCarId());
                reservationDto.
                        setTotalAmount(totalAmount(reservationDto.getDateFrom(),
                                reservationDto.getDateTo(),carDto.getAmountPerDay(),
                                reservationDto.getBranchOfLoanId(),
                                reservationDto.getBranchOfReturnId()));

            }
            else {
                reservationDto.setDateOfBooking(LocalDate.now());
                reservationDto.
                        setTotalAmount(totalAmount(reservationDto.getDateFrom(),
                                reservationDto.getDateTo(),carDto.getAmountPerDay(),
                                reservationDto.getBranchOfLoanId(),
                                reservationDto.getBranchOfReturnId()));
            }
        }
    }

    @Override
    public void cancelReservation(int id) {

        ReservationDto reservationDto = getReservation(id);
        RevenueDto revenue = new RevenueDto();
        if(LocalDate.now().plus(2,ChronoUnit.DAYS).isBefore(reservationDto.getDateFrom())){

            revenue.setCashback(-1 * reservationDto.getTotalAmount());
            revenue.setDate(LocalDate.now());
            revenueService.create(revenue);


            reservationRepository.deleteById(id);
        } else if (LocalDate.now().isAfter(reservationDto.getDateFrom())) {
            throw new RuntimeException("Could not cancel reservation.Date has passed.");
        }else {
            revenue.setCashback(-1 * reservationDto.getTotalAmount() * 0.8);
            revenue.setDate(LocalDate.now());
            revenueService.create(revenue);
            reservationRepository.deleteById(id);
        }

    }

    @Override
    public List<ReservationDto> getReservationByCar(int id) {
        List<Reservation> reservation = reservationRepository.findReservationsByCarId(id);

        return Arrays.asList(modelMapper.map(reservation,ReservationDto[].class));

    }

    public boolean dateCheck(LocalDate fromDate,LocalDate toDate){
        if(fromDate.isAfter(toDate)){
            return false;
        }else
            return true;
    }

    private double totalAmount(LocalDate start,LocalDate end,double amountPerDay,
                               int loanBranch,int returnBranch){
        long daysBetween = ChronoUnit.DAYS.between(start,end) + 1;
        if(loanBranch != returnBranch){
            return daysBetween * amountPerDay + 50;
        }

        return daysBetween * amountPerDay;
    }
}
