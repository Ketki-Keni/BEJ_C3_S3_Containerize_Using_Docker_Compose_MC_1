package com.bej.customer.service;

import com.bej.customer.domain.Customer;
import com.bej.customer.exception.CustomerAlreadyExistsException;
import com.bej.customer.exception.CustomerNotFoundException;

import java.util.List;

public interface CustomerService {
    public Customer login(Customer customer) throws CustomerNotFoundException;
    public Customer register(Customer customer) throws CustomerAlreadyExistsException;
    public List<Customer> getAllCustomers();
    public boolean deleteCustomer(int customerId) throws CustomerNotFoundException;
}
