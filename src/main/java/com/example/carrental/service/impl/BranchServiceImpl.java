package com.example.carrental.service.impl;

import com.example.carrental.dto.BranchDto;
import com.example.carrental.dto.RentalDto;
import com.example.carrental.exception.ResourceNotFoundException;
import com.example.carrental.model.Branch;

import com.example.carrental.model.Rental;
import com.example.carrental.repository.BranchRepository;
import com.example.carrental.repository.RentalRepository;
import com.example.carrental.service.BranchService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BranchServiceImpl implements BranchService {

    private BranchRepository branchRepository;
    private RentalRepository rentalRepository;
    private ModelMapper modelMapper;

    public BranchServiceImpl(BranchRepository branchRepository,
                             RentalRepository rentalRepository,
                             ModelMapper modelMapper){
        this.branchRepository = branchRepository;
        this.rentalRepository = rentalRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public BranchDto create(int rentalId, BranchDto branchDto) {
        Rental rental = rentalRepository.findById(rentalId)
                .orElse(null);
        RentalDto rentalDto = modelMapper.map(rental,RentalDto.class);
        branchDto.setRentalId(rentalDto.getId());

        Branch newBranch = branchRepository.save(modelMapper.map(branchDto,Branch.class));

        return modelMapper.map(newBranch,BranchDto.class);
    }

    @Override
    public List<BranchDto> getBranchesByRentalId(int rentalId) {
        rentalRepository.findById(rentalId).orElseThrow(() ->
                new ResourceNotFoundException("Rental" ,"id",rentalId));

      List<Branch> branches = branchRepository.findByRentalId(rentalId);

       return Arrays.asList(modelMapper.map(branches,BranchDto[].class));
    }

    @Override
    public BranchDto getBranchById(int id) {
    Branch branch = branchRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Branch","id",id));
    return modelMapper.map(branch,BranchDto.class);
    }

    @Override
    public void delete(int id,int rentalId) {
        Branch branch = branchRepository.findByRentalId(rentalId)
                .stream()
                .filter(b->b.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Branch","id",id));

        branchRepository.delete(branch);
    }


}
