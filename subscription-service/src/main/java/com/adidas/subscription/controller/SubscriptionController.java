package com.adidas.subscription.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.adidas.subscription.domain.Subscription;
import com.adidas.subscription.domain.User;
import com.adidas.subscription.dto.SubscriptionRequest;
import com.adidas.subscription.dto.SubscriptionResponse;
import com.adidas.subscription.errorhandling.EmailServiceErrorException;
import com.adidas.subscription.errorhandling.ObjectNotFoundException;
import com.adidas.subscription.errorhandling.SubscriptionUniqueErrorException;
import com.adidas.subscription.service.EmailApiConsumer;
import com.adidas.subscription.service.SubscriptionService;
import com.adidas.subscription.service.UserService;
import com.adidas.subscription.service.impl.ResApiComsumer;
import com.adidas.subscription.util.JsonUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("subscription/api")
@Api(tags={"Subscription Controller"})
public class SubscriptionController {
    
	private static final Logger logger = LoggerFactory.getLogger(SubscriptionController.class);

	@Autowired
	private SubscriptionService subscriptionService;

	@Autowired
	private EmailApiConsumer emailApiConsumer;

    @RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseBody
    @ApiOperation(value = "Create Subscription", notes = "Create a new Subscription.", consumes="application/json", produces="application/json",
	 response = SubscriptionResponse.class, responseContainer = "List")
	SubscriptionResponse createSubscription(@Valid @RequestBody SubscriptionRequest body) throws ObjectNotFoundException, SubscriptionUniqueErrorException, EmailServiceErrorException, Exception {
		logger.info("Creating new subscription");
		Subscription subscription =  this.subscriptionService.createNewSubscription(body);
		SubscriptionResponse response = new SubscriptionResponse();
		emailApiConsumer.sendSubscriptionEmail(body);
		response.populate(subscription);
		logger.info("Subscription  created successfuly" + System.lineSeparator() + JsonUtil.stringify(subscription));
		return response;
	}

    @RequestMapping(value = "/{subscriptionId}", method = RequestMethod.DELETE)
	@ResponseBody
	 void cancelSubscription(@PathVariable Long subscriptionId) throws ObjectNotFoundException, Exception{
		logger.info("Cancelling subscription with Id: "+ subscriptionId);
		this.subscriptionService.remove(subscriptionId);
		logger.info("Subscription with Id: "+ subscriptionId + " cancelled successfuly");
	}

    @RequestMapping(value = "/{subscriptionId}", method = RequestMethod.GET)
	@ResponseBody
	 Subscription getSubscriptionDetails(@PathVariable Long subscriptionId) throws ObjectNotFoundException, Exception{
		logger.info("Getting details for subscription  with Id: "+ subscriptionId);
		return this.subscriptionService.findById(subscriptionId).get();
	}

    @RequestMapping(value = "/all", method = RequestMethod.GET)
	@ResponseBody
	 List<Subscription> getAllSubscriptions() throws Exception{
		logger.info("Getting all subscriptions");
		return this.subscriptionService.findAll();
		
	}
}
