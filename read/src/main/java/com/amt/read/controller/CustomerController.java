package com.amt.read.controller;

import com.amt.read.entity.Customer;
import com.amt.read.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService service;


    /**
     * find a customer
     * @param customerId
     * @return
     * @throws Exception if no customer is found
     */
    @RequestMapping(method = RequestMethod.GET , value = "/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable(value = "customerId") Long customerId) throws Exception {
        Customer customer = service.findById(customerId);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    /**
     * get all the customers
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Customer>> getAll() {
        List<Customer> list = service.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


}
