/*
 * Author : Ketki Keni
 * Date : 06-02-2023
 * Created with : IntelliJ IDEA Community Edition
 */

package com.bej.customer.controller;

import com.bej.customer.domain.Customer;
import com.bej.customer.exception.CustomerAlreadyExistsException;
import com.bej.customer.exception.CustomerNotFoundException;
import com.bej.customer.service.CustomerService;
import com.bej.customer.service.SecurityTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {
    CustomerService customerService;
    SecurityTokenGenerator securityTokenGenerator;

    @Autowired
    public CustomerController(CustomerService customerService, SecurityTokenGenerator securityTokenGenerator) {
        this.customerService = customerService;
        this.securityTokenGenerator = securityTokenGenerator;
    }

    //Uri : http://localhost:8082/api/v1/register
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Customer customer) throws CustomerAlreadyExistsException {
        Customer newCustomer= customerService.register(customer);
        if(newCustomer != null) {
            return new ResponseEntity<String>("Customer registered successfully", HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<String>("Error Occurred", HttpStatus.NOT_FOUND);
        }
    }

    //Uri : http://localhost:8082/api/v1/login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Customer customer) throws CustomerNotFoundException {
        Customer customerLogin = customerService.login(customer);
        if(customerLogin != null) {
            return new ResponseEntity<>(securityTokenGenerator.generateToken(customer), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<String>("Error Occurred", HttpStatus.NOT_FOUND);
        }
    }
}
