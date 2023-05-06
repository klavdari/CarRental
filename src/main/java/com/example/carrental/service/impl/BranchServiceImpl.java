package com.example.carrental.service.impl;

import com.example.carrental.model.Branch;

import com.example.carrental.model.Rental;
import com.example.carrental.repository.BranchRepository;
import com.example.carrental.repository.RentalRepository;
import com.example.carrental.service.BranchService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BranchServiceImpl implements BranchService {

    private BranchRepository branchRepository;
    private RentalRepository rentalRepository;

    public BranchServiceImpl(BranchRepository branchRepository,RentalRepository rentalRepository){
        this.branchRepository = branchRepository;
        this.rentalRepository = rentalRepository;
    }

    @Override
    public Branch create(int rentalId, Branch branch) {
        Rental rental = rentalRepository.findById(rentalId).orElse(null);
        branch.setRental(rental);
        return branchRepository.save(branch);
    }

    @Override
    public List<Branch> getBranchesByRentalId(int rentalId) {
        return branchRepository.findByRentalId(rentalId);
    }

    @Override
    public Branch getBranchById(int id,int rentalId) {
    return branchRepository.findByRentalId(rentalId)
             .stream()
             .filter(b->b.getId() == id)
             .findFirst()
             .orElse(null);
    }

    @Override
    public void delete(int id,int rentalId) {
        Branch branch = branchRepository.findByRentalId(rentalId)
                .stream()
                .filter(b->b.getId() == id)
                .findFirst()
                .orElse(null);

        branchRepository.delete(branch);
    }


}
