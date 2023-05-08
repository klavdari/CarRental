package com.example.carrental.repository;

import com.example.carrental.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface BranchRepository extends JpaRepository<Branch,Integer> {

    @Query(value = "select b from Branch b where b.rental.id = ?1")
    List<Branch> findByRentalId(int rentalId);
}
