package com.adidas.subscription.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.adidas.subscription.domain.Campaign;
import com.adidas.subscription.domain.Subscription;
import com.adidas.subscription.domain.User;
import com.adidas.subscription.dto.SubscriptionRequest;
import com.adidas.subscription.errorhandling.ObjectNotFoundException;
import com.adidas.subscription.errorhandling.SubscriptionUniqueErrorException;
import com.adidas.subscription.persistence.SubscriptionRepository;
import com.adidas.subscription.service.CampaignService;
import com.adidas.subscription.service.SubscriptionService;
import com.adidas.subscription.service.UserService;
import com.adidas.subscription.util.JsonUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * @author lbelluscio
 */
@Component
public class SubscriptionServiceImpl implements SubscriptionService{


    private static final Logger logger = LoggerFactory.getLogger(SubscriptionServiceImpl.class);


    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CampaignService campaignService;

    @Autowired
    CacheManager cacheManager;

    @Override
    public Subscription createNewSubscription(SubscriptionRequest subscriptionDTO) throws ObjectNotFoundException, SubscriptionUniqueErrorException, Exception {
        logger.debug("Creating new Subscription."+ System.lineSeparator()+ JsonUtil.stringify(subscriptionDTO));
        try{
            Optional<User> userOp = this.userService.findByEmail(subscriptionDTO.getEmail());
            User user = userOp.isPresent() ? userOp.get(): this.userService.saveUser(new User(subscriptionDTO.getEmail(), 
                                    subscriptionDTO.getFirstName(), subscriptionDTO.getGender(), subscriptionDTO.getBirthDate()));
                
            Campaign campaign = this.campaignService.findById(subscriptionDTO.getCampaignId()).get();
            if(!this.varifyUniqueSubscription(user, campaign)){
                Subscription subscription = new Subscription(user, campaign, new Date());
                subscription = subscriptionRepository.save(subscription);
                return subscription;
            }else{
                throw new Exception("An error occurs");
            }

        }catch(ObjectNotFoundException objectNotFoundEx){
            throw objectNotFoundEx;
        }catch(SubscriptionUniqueErrorException subscriptionUniqueEx){
            throw subscriptionUniqueEx;
        }

    }

    @Override
    @Cacheable("subscriptions")
    public List<Subscription> findAll() {
        return this.subscriptionRepository.findAll();
    }

    @Override
    @Cacheable("subscription")
    public Optional<Subscription> findById(Long id) throws ObjectNotFoundException {
        logger.debug("Getting Subscription with Id: "+ id);
        Optional<Subscription> subscriptionOp = this.subscriptionRepository.findById(id);
        if(subscriptionOp.isPresent()){
            return subscriptionOp;
        }else{
            throw new ObjectNotFoundException("Subscription with ID: "+ id + " could not be found");
        }
    }

    @Override
    public Subscription save(Subscription subscription) {
        cacheManager.getCache("subscription").invalidate();
        cacheManager.getCache("subscriptions").invalidate();
        logger.debug("Saving new Subscription." + System.lineSeparator() + JsonUtil.stringify(subscription));
        return this.subscriptionRepository.save(subscription);
    }

    
    @Override 
    public void remove(Long id) throws ObjectNotFoundException{
        cacheManager.getCache("subscription").invalidate();
        cacheManager.getCache("subscriptions").invalidate();
        logger.debug("Removing Subscription with Id:" + id);
        try{
            this.subscriptionRepository.deleteById(id);
        }catch(Exception e){
            throw new ObjectNotFoundException("Subscription with ID: "+ id + " could not be found");
        }
        
    }

    /**
     * As a bussiness rule, I considered that a same user can subscribe to multiples Campaigns
     * This method validates that the user is not subscribed to the Campaign
     * @param user
     * @param campaign
     * @return
     * @throws SubscriptionUniqueErrorException
     */
    private Boolean varifyUniqueSubscription(User user, Campaign campaign) throws SubscriptionUniqueErrorException {
        logger.debug("Verifying Subscription.");
        Optional<Subscription> subscription = this.subscriptionRepository.findByUserAndCampaign(user, campaign);
        if(!subscription.isPresent()){
            return false;
        }else {
            throw new SubscriptionUniqueErrorException("The user "+ user.getEmail() + " is already subscribed to campaing "
                + campaign.getCampaignName() + " (id:"+campaign.getId()+")");
        }
    }
    
}
