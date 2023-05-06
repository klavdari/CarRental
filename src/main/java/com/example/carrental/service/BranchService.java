package com.example.carrental.service;

import com.example.carrental.model.Branch;

import java.util.List;

public interface BranchService {

    Branch create(int rentalId,Branch branch);

    List<Branch> getBranchesByRentalId(int rentalId);

    Branch getBranchById(int id,int rentalId);

    void delete(int id,int rentalId );

}
