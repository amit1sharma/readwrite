package com.amt.writereplicate.event;

import com.amt.writereplicate.entity.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


@Aspect
@Component
public class KafkaPublishEventAspect {


//    private static final Logger logger = Logger.getLogger(RedisCacheClearAspect.class);

    @Autowired
    private KafkaProducer kafkaProducer;

    @After("@annotation(kafkaEvent)")
    public void doWork(JoinPoint jp, KafkaPublishEvent kafkaEvent) throws Throwable {
        String eventName = kafkaEvent.eventName();
        if(!StringUtils.hasLength(eventName)){
            throw new Exception("EventName is required to publish and event!");
        }

        Object[] args = jp.getArgs();
        Long key = 0l;
        Customer c = null;
        if(args!=null && args.length>0){
            if(args[0] instanceof Customer){
                c = (Customer)args[0];
                key = c.getId();
            }else if(args[0] instanceof Long) {
                key = (Long)args[0];
                c = new Customer();
                c.setId(key);
            }
        }else{
            return;
        }

        pushMessage(eventName, key,  c );


    }

    public void pushMessage(String topic, Long key, Customer customer) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String customerJson  = mapper.writeValueAsString(customer);
        System.out.println(" Pushing data " + customerJson + "on topic " + topic);
        ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>(topic, key+"",customerJson);
        System.out.println(" Producer Record created : " + producerRecord.toString());
        kafkaProducer.send(producerRecord);
        System.out.println(" Data pushed successfully '" + customerJson + "' on topic " + topic);
    }
}
