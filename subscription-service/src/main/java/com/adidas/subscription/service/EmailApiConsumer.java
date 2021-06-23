package com.adidas.subscription.service;

import com.adidas.subscription.dto.SubscriptionRequest;
import com.adidas.subscription.errorhandling.EmailServiceErrorException;

/**
 * @author
 */
public interface EmailApiConsumer {
    
    /**
     * Notify to Email service API that a new Subscription has been created
     * Email Service will sent a confirmation mail to the new user
     * @param request
     * @param accessToken
     * @return
     * @throws EmailServiceErrorException
     */
    String sendSubscriptionEmail(SubscriptionRequest request, String accessToken) throws EmailServiceErrorException;

}
