package com.amt.writereplicate.event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface KafkaPublishEvent {

    /**
     * This is to be used to publis event to kafka
     * @return
     */
    public String eventName() default "";
}