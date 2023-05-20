package com.example.carrental.controller;

import com.example.carrental.dto.LoanDto;
import com.example.carrental.model.Loan;
import com.example.carrental.service.LoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loan")
public class LoanController {

    private LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    public ResponseEntity<LoanDto> addLoan(@RequestBody LoanDto loanDto){
        return new ResponseEntity<>(loanService.createLoan(loanDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<LoanDto>> getLoans(){
        return new ResponseEntity<>(loanService.getLoans(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanDto> getLoan(@PathVariable int id){
        return new ResponseEntity<>(loanService.getLoan(id),HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<LoanDto> updateLoan(@RequestBody LoanDto loan,int id){
        return new ResponseEntity<>(loanService.updateLoan(loan,id),HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteLoan(int id){
        loanService.deleteLoan(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
