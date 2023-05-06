package com.example.carrental.repository;

import com.example.carrental.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BranchRepository extends JpaRepository<Branch,Integer> {

    List<Branch> findByRentalId(int rentalId);
}
