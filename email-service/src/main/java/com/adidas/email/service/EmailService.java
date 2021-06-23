package com.adidas.email.service;

import com.adidas.email.dto.EmailTemplate;
import com.adidas.email.dto.SubscriptionRequest;

/**
 * @author lbelluscio
 */
public interface EmailService {
    
     EmailTemplate sendEmail(SubscriptionRequest subscriptionRequest);

}
