package com.example.carrental.controller;

import com.example.carrental.dto.EmployeeDto;
import com.example.carrental.model.Employee;
import com.example.carrental.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> addEmployee(@Valid @RequestBody EmployeeDto employeeDto){
        return new ResponseEntity<>(employeeService.addNewEmployee(employeeDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getEmployees(){
        return new ResponseEntity<>(employeeService.getEmployees(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable int id){
        return new ResponseEntity<>(employeeService.getById(id),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@Valid @RequestBody EmployeeDto employeeDto, @PathVariable int id){
        return new ResponseEntity<>(employeeService.updateEmployee(employeeDto,id),HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteEmployee(int id){
        employeeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
