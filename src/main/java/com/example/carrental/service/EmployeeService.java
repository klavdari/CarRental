package com.example.carrental.service;



import com.example.carrental.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee addNewEmployee(Employee employee);

    List<Employee> getEmployees();

    Employee getById(int id);

    Employee updateEmployee(Employee employee,int id);

    void delete(int id);
}
