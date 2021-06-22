package com.adidas.subscription.service;

import com.adidas.subscription.dto.SubscriptionRequest;
import com.adidas.subscription.errorhandling.EmailServiceErrorException;

public interface EmailApiConsumer {
    
    String sendSubscriptionEmail(SubscriptionRequest request) throws EmailServiceErrorException;

}
