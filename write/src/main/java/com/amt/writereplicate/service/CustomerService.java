package com.amt.writereplicate.service;

import com.amt.writereplicate.entity.Customer;

import javax.transaction.Transactional;
import java.util.List;

public interface CustomerService {
    public void save(Customer request);

    @Transactional
    void update(Customer cust);

    Customer findById(long id) throws Exception;

    void delete(Long customerId);
}
