package com.amt.replicator.consumers;

import com.amt.replicator.entity.Customer;
import com.amt.replicator.repository.CustomerRepository;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Component
public class Consumer {

    @Autowired
    private CustomerRepository customerRepository;

    private static final ObjectMapper mapper = new ObjectMapper();

    @KafkaListener(topics = "save")
    public void saveEvent(String message){

        System.out.println("Message received: " + message);

        if(!StringUtils.hasLength(message)) {
        }
        else
        {
            try{
                mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
                JsonNode msg = mapper.readTree(message);
                Customer entity = mapper.convertValue(msg, Customer.class);
                customerRepository.save(entity);
                System.out.println("saved successfully.");
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            } catch ( Exception e) {
                e.printStackTrace();
            }
        }
    }

    @KafkaListener(topics = "update")
    public void updateEvent(String message){

        System.out.println("Message received: " + message);

        if(!StringUtils.hasLength(message)) {
        }
        else
        {
            try{
                mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
                JsonNode msg = mapper.readTree(message);
                Customer entity = mapper.convertValue(msg, Customer.class);
                Customer customer = customerRepository.findById(entity.getId()).orElseThrow(()->new Exception("Customer not present"));
                customer.setMarketingPreference(entity.getMarketingPreference());
                customerRepository.save(customer);
                System.out.println("saved successfully.");
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            } catch ( Exception e) {
                e.printStackTrace();
            }
        }
    }
    @KafkaListener(topics = "delete")
    public void deleteEvent(String message){

        System.out.println("Message received: " + message);

        if(!StringUtils.hasLength(message)) {
        }
        else
        {
            try{
                mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
                JsonNode msg = mapper.readTree(message);
                Customer entity = mapper.convertValue(msg, Customer.class);
                customerRepository.deleteById(entity.getId());
                System.out.println("saved successfully.");
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            } catch ( Exception e) {
                e.printStackTrace();
            }
        }
    }
}