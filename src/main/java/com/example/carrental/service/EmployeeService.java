package com.example.carrental.service;



import com.example.carrental.dto.EmployeeDto;
import com.example.carrental.model.Employee;

import java.util.List;

public interface EmployeeService {
    EmployeeDto addNewEmployee(EmployeeDto employeeDto);

    List<EmployeeDto> getEmployees();

    EmployeeDto getById(int id);

    EmployeeDto updateEmployee(EmployeeDto employee,int id);

    void delete(int id);
}
