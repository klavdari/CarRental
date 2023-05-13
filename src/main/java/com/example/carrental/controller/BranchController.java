package com.example.carrental.controller;

import com.example.carrental.model.Branch;
import com.example.carrental.service.BranchService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
public class BranchController {


    private BranchService branchService;

    @Autowired
    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @PostMapping("/branch")
    public ResponseEntity<Branch> addBranch(int rentalId,
                                           @Valid @RequestBody Branch branch){
        Branch newBranch= branchService.create(rentalId,branch);
        return new ResponseEntity<>(newBranch, HttpStatus.CREATED);
    }

    @GetMapping("/branch")
    public List<Branch> getBranchesByRentalId(int rentalId){
        return branchService.getBranchesByRentalId(rentalId);
    }

    @GetMapping("/branch/{branchId}")
    public Branch getBranchById(@PathVariable("branchId") int id, int rentalId){
        return branchService.getBranchById(id,rentalId);
    }

    @DeleteMapping("/branch/{branchId}")
    public ResponseEntity deleteBranchById(@PathVariable("branchId") int id, int rentalId){
        branchService.delete(id,rentalId);
        return new ResponseEntity(HttpStatus.OK);
    }


}
