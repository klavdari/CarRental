package com.example.carrental.service.impl;

import com.example.carrental.exception.ResourceNotFoundException;
import com.example.carrental.model.Customer;
import com.example.carrental.repository.CustomerRepository;
import com.example.carrental.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(int id) {

        return customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer","id",id));
    }

    @Override
    public void deleteCustomerById(int id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Customer updateCustomer(Customer customer, int id) {
        Customer newCustomer = getCustomerById(id);

        newCustomer.setAddress(customer.getAddress());
        newCustomer.setEmail(customer.getEmail());
        newCustomer.setFirstName(customer.getFirstName());
        newCustomer.setLastName(customer.getLastName());

        return customerRepository.save(newCustomer);
    }
}
