package com.example.carrental.service.impl;

import com.example.carrental.exception.ResourceNotFoundException;
import com.example.carrental.model.Employee;
import com.example.carrental.repository.EmployeeRepository;
import com.example.carrental.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee addNewEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getById(int id) {

        return employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee","id",id));
    }

    @Override
    public Employee updateEmployee(Employee newEmployee, int id) {
        Employee employee = getById(id);

        employee.setPosition(newEmployee.getPosition());
        employee.setWorkingBranch(newEmployee.getWorkingBranch());

        return employee;
    }

    @Override
    public void delete(int id) {
        employeeRepository.deleteById(id);
    }
}
