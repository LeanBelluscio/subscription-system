package com.adidas.subscription;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;


/**
 * @author lbelluscio
 */
@SpringBootApplication
@ComponentScan
@EnableCaching
class SubscriptionApplication {
    
    public static void main(String[] args) {

    	SpringApplication.run(SubscriptionApplication.class, args);
    }
    
}
