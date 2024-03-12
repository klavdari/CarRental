package com.example.carrental.service.impl;

import com.example.carrental.dto.CarDto;
import com.example.carrental.dto.LoanDto;
import com.example.carrental.dto.ReservationDto;
import com.example.carrental.exception.ResourceNotFoundException;
import com.example.carrental.model.Car;
import com.example.carrental.model.Loan;
import com.example.carrental.model.ReservationStatus;
import com.example.carrental.repository.CarRepository;
import com.example.carrental.repository.LoanRepository;
import com.example.carrental.service.LoanService;
import com.example.carrental.service.ReservationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
@Service
public class LoanServiceImpl implements LoanService {

    private LoanRepository loanRepository;
    private ModelMapper modelMapper;

    private CarRepository carRepository;

    private ReservationService reservationService;

    public LoanServiceImpl(LoanRepository loanRepository, ModelMapper modelMapper,
                           ReservationService reservationService, CarRepository carRepository) {
        this.loanRepository = loanRepository;
        this.modelMapper = modelMapper;
        this.reservationService = reservationService;
        this.carRepository = carRepository;
    }

    @Override
    public LoanDto createLoan(LoanDto loanDto) {

        Car car = carRepository.findById(loanDto.getReservationCarId()).orElseThrow();
        CarDto carDto = modelMapper.map(car,CarDto.class);



        ReservationDto reservationDto = new ReservationDto();


        loanDto.setReservationStatus(ReservationStatus.LOANED);

        modelMapper.map(loanDto,reservationDto);


        reservationService.reservationCheck(reservationDto,carDto);



        Loan loan = modelMapper.map(loanDto,Loan.class);
        loan = loanRepository.save(loan);
        return modelMapper.map(loan,LoanDto.class);

    }


    @Override
    public List<LoanDto> getLoans() {

        return Arrays.asList(modelMapper.map(loanRepository.findAll(),LoanDto[].class));
    }

    @Override
    public LoanDto getLoan(int id) {
        Loan loan = loanRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Loan with id not found","id",id));

        return modelMapper.map(loan,LoanDto.class);
    }

    @Override
    public LoanDto updateLoan(LoanDto loanDto, int id) {

       LoanDto newLoan = getLoan(id);

        newLoan.setComments(loanDto.getComments());
        newLoan.setEmployeeId(loanDto.getEmployeeId());
        newLoan.setReservationId(loanDto.getReservationId());

        Loan loan = loanRepository.save(modelMapper.map(newLoan,Loan.class)) ;
        return modelMapper.map(loan,LoanDto.class);
    }

    @Override
    public void deleteLoan(int id) {
        loanRepository.deleteById(id);
    }
}
