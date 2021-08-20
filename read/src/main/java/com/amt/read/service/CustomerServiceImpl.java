package com.amt.read.service;

import com.amt.read.entity.Customer;
import com.amt.read.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements  CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer findById(long id) throws Exception {
        return customerRepository.findById(id).orElseThrow(()-> new Exception("Customer not found with id : "+id));
    }

    @Override
    public List<Customer> findAll(){
//        PageRequest.of()
        return customerRepository.findAll();
    }


}
