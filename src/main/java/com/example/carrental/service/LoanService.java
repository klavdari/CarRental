package com.example.carrental.service;

import com.example.carrental.dto.LoanDto;
import com.example.carrental.model.Loan;

import java.util.List;

public interface LoanService {

    LoanDto createLoan(LoanDto loan);

    List<LoanDto> getLoans();

    LoanDto getLoan(int id);

    LoanDto updateLoan(LoanDto loan,int id);

    void deleteLoan(int id);
}
