package com.adidas.publication.service;

import com.adidas.publication.dto.SubscriptionRequest;

/**
 * @author lbelluscio
 */
public interface SubscriptionServiceConsumer {
  
    /**
     * Calls Subscrition service API
     * POST METHOD /subscription/api/
     *
     * @param request
     * @return
     */
    String subscribe(SubscriptionRequest request);
}
