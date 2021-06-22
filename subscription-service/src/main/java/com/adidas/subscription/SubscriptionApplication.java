package com.adidas.subscription;

import java.time.Instant;
import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.adidas.subscription.util.DateConverter;


@SpringBootApplication
@ComponentScan
@EnableCaching
class SubscriptionApplication {
    
    public static void main(String[] args) {

    	SpringApplication.run(SubscriptionApplication.class, args);
    }
    
}
