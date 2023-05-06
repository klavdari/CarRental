package com.example.carrental.service;

import com.example.carrental.model.Customer;

import java.util.List;

public interface CustomerService {
    Customer create(Customer customer);

    List<Customer> getCustomers();

    Customer getCustomerById(int id);

    void deleteCustomerById(int id);
}
