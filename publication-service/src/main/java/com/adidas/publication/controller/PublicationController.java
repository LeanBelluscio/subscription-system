package com.adidas.publication.controller;

import java.io.IOException;

import javax.validation.Valid;

import com.adidas.publication.dto.SubscriptionRequest;
import com.adidas.publication.errorhandling.SubscriptionServiceErrorException;
import com.adidas.publication.service.SubscriptionServiceConsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author lbelluscio
 * 
 */
@RestController
@RequestMapping("publication/api")
@Api(tags={"publication Controller"})
public class PublicationController {
    
	private static final Logger logger = LoggerFactory.getLogger(PublicationController.class);

	@Autowired
	private SubscriptionServiceConsumer subscriptionServiceConsumer;

    @RequestMapping(value = "/subscription", method = RequestMethod.POST)
	@ResponseBody
    @ApiOperation(value = "Create Subscription", notes = "Create a new Subscription.", consumes="application/json", produces="application/json",
	 responseContainer = "List")
	 ResponseEntity<String> newsletterSubscription(@Valid @RequestBody SubscriptionRequest body) throws SubscriptionServiceErrorException, IOException, Exception{
		logger.info("Creatign new Subscription");
		String response = this.subscriptionServiceConsumer.subscribe(body);
		return ResponseEntity.ok(response);
	}

}  
	
