package com.example.customermanager.service;

import com.example.customermanager.model.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> findAll();

    void save(Customer customer);

    Customer findById(int id);

    void update(int id, Customer customer);

    void remove(int id);
    List<Customer> findAll(String address);
    List<Customer> sortByName();
}
