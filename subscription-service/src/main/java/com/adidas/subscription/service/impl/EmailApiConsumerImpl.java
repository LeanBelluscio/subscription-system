package com.adidas.subscription.service.impl;

import com.adidas.subscription.dto.SubscriptionRequest;
import com.adidas.subscription.errorhandling.EmailServiceErrorException;
import com.adidas.subscription.service.EmailApiConsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author lbelluscio
 */
@Component
public class EmailApiConsumerImpl extends ResApiComsumer implements EmailApiConsumer{

    private static final Logger logger = LoggerFactory.getLogger(EmailApiConsumerImpl.class);


    @Value("${email.service.subscription_endpoint}")
    private String subscriptionEndpoint;
    

    @Autowired
    public EmailApiConsumerImpl(@Value("${email.service.base_path}") String basePath){
        this.basePath = basePath;
    }

    @Override
    public String sendSubscriptionEmail(SubscriptionRequest request, String accessToken) throws EmailServiceErrorException {
        logger.info("Calling email service");
        try{
            String emailServiceResponse =  post(request, subscriptionEndpoint, accessToken).getBody();
            logger.info("Email Sent:" + emailServiceResponse);
            return emailServiceResponse;
        }catch(Exception e){
            throw new EmailServiceErrorException("Error Calling Email Service Api" + System.lineSeparator() + e.getMessage());
        }
    }

    
}
