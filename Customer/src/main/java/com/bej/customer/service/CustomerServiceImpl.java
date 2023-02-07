/*
 * Author : Ketki Keni
 * Date : 06-02-2023
 * Created with : IntelliJ IDEA Community Edition
 */

package com.bej.customer.service;

import com.bej.customer.domain.Customer;
import com.bej.customer.exception.CustomerAlreadyExistsException;
import com.bej.customer.exception.CustomerNotFoundException;
import com.bej.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{

    CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer login(Customer customer) throws CustomerNotFoundException {
        Customer customer1=customerRepository.findById(customer.getCustomerId()).get();
        if(customer1 != null){
            if (customer1.getCustomerPassword().equals(customer.getCustomerPassword())) {
                return customer1;
            }
            else {
                return null;
            }
        }
        else {
            return null;
        }
    }

    @Override
    public Customer register(Customer customer) throws CustomerAlreadyExistsException {
        if(customerRepository.findById(customer.getCustomerId()).isPresent()){
            throw new CustomerAlreadyExistsException();
        }
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public boolean deleteCustomer(int customerId) throws CustomerNotFoundException {
        boolean flag= false;
        if(customerRepository.findById(customerId).isEmpty()){
            throw new CustomerNotFoundException();
        }
        else {
            customerRepository.deleteById(customerId);
            flag = true;
        }
        return flag;
    }
}
