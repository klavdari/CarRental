package com.example.carrental.dao;

import com.example.carrental.model.Branch;
import com.example.carrental.model.Rental;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RentalDAOImpl implements RentalDAO{

    private EntityManager entityManager;

    @Autowired
    public RentalDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Rental create(Rental rental) {

        Rental dbRental = entityManager.merge(rental);

        return dbRental;
    }

    @Override
    public Branch create(Branch branch) {

        Branch dbBranch = entityManager.merge(branch);

        return dbBranch;
    }

    @Override
    public void deleteBranchById(int id) {

    Branch branch = entityManager.find(Branch.class,id);

    entityManager.remove(branch);
    }
}
