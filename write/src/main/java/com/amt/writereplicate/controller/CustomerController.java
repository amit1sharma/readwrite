package com.amt.writereplicate.controller;

import com.amt.writereplicate.entity.Customer;
import com.amt.writereplicate.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService service;

    /**
     * creates a customer
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity saveCustomer(@Validated @RequestBody Customer request){
        service.save(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Update preference of customer
     * @param customerId
     * @param customerRequest
     * @return updated customer
     * @throws Exception throws exception if no record is for customerid
     */
    @RequestMapping(method = RequestMethod.PUT, value="/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable(value = "customerId") Long customerId, @Valid @RequestBody Customer customerRequest) throws Exception {

        Customer customer = service.findById(customerId);
        customer.setMarketingPreference(customerRequest.getMarketingPreference());

        service.update(customer);

        return new ResponseEntity(customer,HttpStatus.OK);
    }

    /**
     * Delete a customer
     * @param customerId
     * @return
     * @throws throws Exception if the resource is not found
     */
    @RequestMapping(method = RequestMethod.DELETE, value="/{customerId}")
    public ResponseEntity deleteCustomer(@PathVariable(value = "customerId") Long customerId){
        service.delete(customerId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
