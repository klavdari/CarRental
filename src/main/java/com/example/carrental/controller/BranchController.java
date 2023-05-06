package com.example.carrental.controller;

import com.example.carrental.model.Branch;
import com.example.carrental.model.Rental;
import com.example.carrental.service.BranchService;
import com.example.carrental.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rental/{rentalId}")
public class BranchController {

    @Autowired
    private BranchService branchService;

    @PostMapping("/branch")
    public ResponseEntity<Branch> addBranch(@PathVariable(value = "rentalId") int rentalId,
                                            @RequestBody Branch branch){
        Branch newBranch= branchService.create(rentalId,branch);
        return new ResponseEntity<>(newBranch, HttpStatus.CREATED);
    }

    @GetMapping("/branch")
    public List<Branch> getBranchesByRentalId(@PathVariable(value = "rentalId") int rentalId){
        return branchService.getBranchesByRentalId(rentalId);
    }

    @GetMapping("/branch/{branchId}")
    public Branch getBranchById(@PathVariable("branchId") int id, @PathVariable("rentalId") int rentalId){
        return branchService.getBranchById(id,rentalId);
    }

    @DeleteMapping("/branch/{branchId}")
    public ResponseEntity deleteBranchById(@PathVariable("branchId") int id, @PathVariable("rentalId") int rentalId){
        branchService.delete(id,rentalId);
        return new ResponseEntity(HttpStatus.OK);
    }


}
