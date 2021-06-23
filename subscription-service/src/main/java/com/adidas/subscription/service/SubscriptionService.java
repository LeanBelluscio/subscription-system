package com.adidas.subscription.service;

import java.util.List;
import java.util.Optional;

import com.adidas.subscription.domain.Subscription;
import com.adidas.subscription.dto.SubscriptionRequest;
import com.adidas.subscription.errorhandling.ObjectNotFoundException;
import com.adidas.subscription.errorhandling.SubscriptionUniqueErrorException;

/**
 * @author lbelluscio
 */
public interface SubscriptionService {
    
    Optional<Subscription> findById(Long id);

    List<Subscription> findAll();

    Subscription createNewSubscription(SubscriptionRequest subscriptionDTO) throws ObjectNotFoundException, SubscriptionUniqueErrorException, Exception ;

    Subscription save(Subscription subscription);

    void remove(Long id);
}
