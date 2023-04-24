package com.example.carrental.dao;

import com.example.carrental.model.Branch;
import com.example.carrental.model.Rental;

public interface RentalDAO {

    Rental create(Rental rental);

    Branch create(Branch branch);

    void  deleteBranchById(int id);
}
