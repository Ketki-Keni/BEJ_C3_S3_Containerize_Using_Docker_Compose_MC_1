/*
 * Author : Ketki Keni
 * Date : 06-02-2023
 * Created with : IntelliJ IDEA Community Edition
 */

package com.bej.customer.controller;

import com.bej.customer.domain.Customer;
import com.bej.customer.exception.CustomerNotFoundException;
import com.bej.customer.service.CustomerService;
import com.bej.customer.service.SecurityTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2")
public class CustomerControllerV2 {
    CustomerService customerService;
    SecurityTokenGenerator securityTokenGenerator;

    @Autowired
    public CustomerControllerV2(CustomerService customerService, SecurityTokenGenerator securityTokenGenerator) {
        this.customerService = customerService;
        this.securityTokenGenerator = securityTokenGenerator;
    }
    @GetMapping("/customers")
    public ResponseEntity<?> getAllCustomers(){
        List<Customer> allCustomers = customerService.getAllCustomers();
        if(allCustomers.size()>0){
            return new ResponseEntity<List<Customer>>(allCustomers, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<String>("No Customers Found",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/customer/{customerId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable int customerId) throws CustomerNotFoundException {
        if(customerService.deleteCustomer(customerId)){
            return new ResponseEntity<String>("Customer deleted", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<String>("Error Occurred", HttpStatus.NOT_FOUND);
        }
    }
}
