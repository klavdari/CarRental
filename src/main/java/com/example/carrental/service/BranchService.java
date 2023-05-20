package com.example.carrental.service;

import com.example.carrental.dto.BranchDto;
import com.example.carrental.model.Branch;

import java.util.List;

public interface BranchService {

    BranchDto create(int rentalId, BranchDto branchDto);

    List<BranchDto> getBranchesByRentalId(int rentalId);

    BranchDto getBranchById(int id,int rentalId);

    void delete(int id,int rentalId );

}
