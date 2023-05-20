package com.example.carrental.service.impl;

import com.example.carrental.dto.LoanDto;
import com.example.carrental.exception.ResourceNotFoundException;
import com.example.carrental.model.Loan;
import com.example.carrental.repository.LoanRepository;
import com.example.carrental.service.LoanService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
@Service
public class LoanServiceImpl implements LoanService {

    private LoanRepository loanRepository;
    private ModelMapper modelMapper;

    public LoanServiceImpl(LoanRepository loanRepository,ModelMapper modelMapper) {
        this.loanRepository = loanRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public LoanDto createLoan(LoanDto loanDto) {

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
