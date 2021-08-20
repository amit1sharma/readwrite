package com.amt.writereplicate.service;

import com.amt.writereplicate.entity.Customer;
import com.amt.writereplicate.event.KafkaPublishEvent;
import com.amt.writereplicate.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.awt.print.Pageable;
import java.util.List;

@Service
public class CustomerServiceImpl implements  CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    @Transactional
    @KafkaPublishEvent(eventName = "save")
    public void save(Customer cust) {
        customerRepository.save(cust);
    }

    @Override
    @Transactional
    @KafkaPublishEvent(eventName = "update")
    public void update(Customer cust) {
        save(cust);
    }

    @Override
    public Customer findById(long id) throws Exception {
        return customerRepository.findById(id).orElseThrow(() -> new Exception("Customer not found with id : " + id));
    }

    @Override
    @KafkaPublishEvent(eventName = "delete")
    public void delete(Long customerId){
        customerRepository.deleteById(customerId);
    }


}
