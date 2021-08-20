package com.amt.read.service;

import com.amt.read.entity.Customer;

import java.util.List;

public interface CustomerService {

    Customer findById(long id) throws Exception;

    List<Customer> findAll();

}
