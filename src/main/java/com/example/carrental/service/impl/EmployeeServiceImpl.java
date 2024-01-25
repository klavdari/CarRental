package com.example.carrental.service.impl;

import com.example.carrental.dto.BranchDto;
import com.example.carrental.dto.EmployeeDto;
import com.example.carrental.exception.ResourceNotFoundException;
import com.example.carrental.model.Branch;
import com.example.carrental.model.Employee;
import com.example.carrental.model.Position;
import com.example.carrental.repository.BranchRepository;
import com.example.carrental.repository.EmployeeRepository;
import com.example.carrental.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private BranchRepository branchRepository;
    private ModelMapper modelMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               ModelMapper modelMapper,
                               BranchRepository branchRepository) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
        this.branchRepository = branchRepository;
    }

    @Override
    public EmployeeDto addNewEmployee(EmployeeDto employeeDto) {

        Branch branch = branchRepository.findById(employeeDto.getWorkingBranchId()).orElseThrow(() ->
                new ResourceNotFoundException("Branch", "id", employeeDto.getWorkingBranchId()));

        BranchDto branchDto = modelMapper.map(branch, BranchDto.class);

        if (branchDto.isActive() && isManager(employeeDto)) {
            throw new RuntimeException("Manager exist in selected branch");
        } else if (!branchDto.isActive() && isManager(employeeDto)) {
            branchDto.setActive(true);
            branchRepository.save(modelMapper.map(branchDto, Branch.class));
        }

        Employee employee = modelMapper.map(employeeDto, Employee.class);
        employee.setWorkingBranch(branch);
        Employee newEmployee = employeeRepository.save(employee);
        return modelMapper.map(employee, EmployeeDto.class);
    }

    @Override
    public List<EmployeeDto> getEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return Arrays.asList(modelMapper.map(employees, EmployeeDto[].class));
    }

    @Override
    public EmployeeDto getById(int id) {

        Employee employee = employeeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Employee", "id", id));
        return modelMapper.map(employee, EmployeeDto.class);
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto newEmployee, int id) {
        EmployeeDto employeeDto = getById(id);
        Branch branch = branchRepository.findById(employeeDto.getWorkingBranchId()).orElseThrow(() ->
                new ResourceNotFoundException("Branch", "id", employeeDto.getWorkingBranchId()));

        Branch newBranch = branchRepository.findById(newEmployee.getWorkingBranchId()).orElseThrow(() ->
                new RuntimeException("Branch doesn't exist"));


        if (isManager(employeeDto) && newEmployee.getPosition() == Position.EMPLOYEE) {
            branch.setActive(false);
        } else if (!branch.isActive() && newEmployee.getPosition() == Position.MANAGER) {
            branch.setActive(true);
        } else if (isManager(employeeDto) && newEmployee.getPosition() == Position.MANAGER) {
            throw new RuntimeException("Manager exist in selected branch");
        } else if (newBranch.isActive() && newEmployee.getPosition() == Position.MANAGER) {
            throw new RuntimeException("Manager exist in newly selected branch");
        }
        employeeDto.setPosition(newEmployee.getPosition());
        employeeDto.setWorkingBranchId(newEmployee.getWorkingBranchId());

        Employee employee = employeeRepository.save(modelMapper.map(employeeDto, Employee.class));

        return modelMapper.map(employee, EmployeeDto.class);
    }

    @Override
    public void delete(int id) {
        EmployeeDto employeeDto = getById(id);
        if (isManager(employeeDto)) {
            Branch branch = branchRepository
                    .findById(employeeDto
                            .getWorkingBranchId()).orElseThrow();
            branch.setActive(false);
        }
        employeeRepository.deleteById(id);
    }


    private boolean isManager(EmployeeDto employeeDto) {
        return employeeDto.getPosition() == Position.MANAGER;
    }
}
