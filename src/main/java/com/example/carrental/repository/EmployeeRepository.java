package com.example.carrental.repository;

import com.example.carrental.model.Branch;
import com.example.carrental.model.Employee;
import com.example.carrental.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    int countByWorkingBranchAndPosition(Branch branch, Position position);
}
