package com.adidas.email.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.adidas.email.dto.SubscriptionRequest;
import com.adidas.email.errorhandling.ObjectNotFoundException;
import com.adidas.email.util.EmailSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/email/api")
@Api(tags={"Email Controller"})
public class EmailController {
    

	@Autowired
	private EmailSender emailSender;

    @RequestMapping(value = "/subscription", method = RequestMethod.POST)
	@ResponseBody
    @ApiOperation(value = "Send Email for new subscription", notes = "Email for new subscription", consumes="application/json", produces="application/json",
	 responseContainer = "List")
	ResponseEntity sendEmailSubscription(@Valid @RequestBody SubscriptionRequest body)  {
		
		this.emailSender.sendEmail(body);
		return ResponseEntity.ok("Email sent");
	}

	
}  
	
