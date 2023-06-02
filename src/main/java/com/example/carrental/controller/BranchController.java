package com.example.carrental.controller;

import com.example.carrental.dto.BranchDto;
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
    public ResponseEntity<BranchDto> addBranch(int rentalId,
                                               @Valid @RequestBody BranchDto branchDto){
        BranchDto newBranch= branchService.create(rentalId,branchDto);
        return new ResponseEntity<>(newBranch, HttpStatus.CREATED);
    }

    @GetMapping("/branch")
    public  List<BranchDto> getBranchesByRentalId(int rentalId){
        return branchService.getBranchesByRentalId(rentalId);
    }

    @GetMapping("/branch/{branchId}")
    public BranchDto getBranchById(@PathVariable("branchId") int id){
        return branchService.getBranchById(id);
    }

    @DeleteMapping("/branch/{branchId}")
    public ResponseEntity deleteBranchById(@PathVariable("branchId") int id, int rentalId){
        branchService.delete(id,rentalId);
        return new ResponseEntity(HttpStatus.OK);
    }


}
