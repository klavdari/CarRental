package com.example.carrental.service;

import com.example.carrental.model.Loan;

import java.util.List;

public interface LoanService {

    Loan createLoan(Loan loan);

    List<Loan> getLoans();

    Loan getLoan(int id);

    Loan updateLoan(Loan loan,int id);

    void deleteLoan(int id);
}
