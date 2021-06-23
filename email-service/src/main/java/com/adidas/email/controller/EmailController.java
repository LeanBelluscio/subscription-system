package com.adidas.email.controller;

import javax.validation.Valid;

import com.adidas.email.dto.EmailTemplate;
import com.adidas.email.dto.SubscriptionRequest;
import com.adidas.email.service.EmailService;

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
 */
@RestController
@RequestMapping("/email/api")
@Api(tags={"Email Controller"})
public class EmailController {
    

	@Autowired
	private EmailService emailService;

    @RequestMapping(value = "/subscription", method = RequestMethod.POST)
	@ResponseBody
    @ApiOperation(value = "Send Email for new subscription", notes = "Email for new subscription", consumes="application/json", produces="application/json",
	 responseContainer = "List")
	ResponseEntity<EmailTemplate> sendEmailSubscription(@Valid @RequestBody SubscriptionRequest request)  {
		
		EmailTemplate emailTemplate = this.emailService.sendEmail(request);
		return ResponseEntity.ok(emailTemplate);
	}

	
}  
	
