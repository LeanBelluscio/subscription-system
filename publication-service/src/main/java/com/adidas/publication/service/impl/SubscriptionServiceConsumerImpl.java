package com.adidas.publication.service.impl;

import com.adidas.publication.dto.SubscriptionRequest;
import com.adidas.publication.errorhandling.SubscriptionServiceErrorException;
import com.adidas.publication.service.SubscriptionServiceConsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author lbelluscio
 */
@Component
public class SubscriptionServiceConsumerImpl extends ResApiComsumer implements SubscriptionServiceConsumer {


    @Value("${subscription.service.subscription_endpoint}")
    private String subscriptionEndpoint;
    

    @Autowired
    public SubscriptionServiceConsumerImpl(@Value("${subscription.service.base_path}") String basePath){
        this.basePath = basePath;
    }

  
    @Override
    public String subscribe(SubscriptionRequest request) {
        try{
            return post(request, subscriptionEndpoint).getBody();
        }catch(Exception e){
            throw new SubscriptionServiceErrorException("Error Calling Subscription Service Api" + System.lineSeparator() + e.getMessage());
        }
    }
    
}
