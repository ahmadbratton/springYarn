package com.example.customer.service;

import com.example.customer.model.Customer;

import java.util.List;

public interface CustomerService {


    Customer add(Customer customer);

    Customer update(Customer customer);

    Customer getById(int id);

    List<Customer> get();

    void delete(int id);
}
