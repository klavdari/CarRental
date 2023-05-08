package com.example.carrental.service.impl;

import com.example.carrental.model.Loan;
import com.example.carrental.repository.LoanRepository;
import com.example.carrental.service.LoanService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LoanServiceImpl implements LoanService {

    private LoanRepository loanRepository;

    public LoanServiceImpl(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Override
    public Loan createLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    @Override
    public List<Loan> getLoans() {
        return loanRepository.findAll();
    }

    @Override
    public Loan getLoan(int id) {
        return loanRepository.findById(id).orElse(null);
    }

    @Override
    public Loan updateLoan(Loan loan, int id) {

        Loan newLoan = getLoan(id);

        newLoan.setComments(loan.getComments());
        newLoan.setEmployee(loan.getEmployee());
        newLoan.setReservation(loan.getReservation());
        return loanRepository.save(loan);
    }

    @Override
    public void deleteLoan(int id) {
        loanRepository.deleteById(id);
    }
}
