package com.adidas.email.service.impl;

import com.adidas.email.dto.EmailTemplate;
import com.adidas.email.dto.SubscriptionRequest;
import com.adidas.email.service.EmailService;
import com.adidas.email.util.JsonUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author lbelluscio
 */
@Component
public class EmailServiceImpl implements EmailService{

    private static final Logger logger = LoggerFactory.getLogger(EmailTemplate.class);

    @Value("${email.inbox.from}")
    private String from;

    @Value("${email.inbox.subject}")
    private String subject;

    @Value("${email.inbox.body}")
    private String bodyTemplate;

    /**
     * Dummy method, shoud create MailMessage and send an email
     */
    @Override
    public EmailTemplate sendEmail(SubscriptionRequest subscriptionRequest) {
       
        EmailTemplate emailTemplate = new EmailTemplate();
        emailTemplate.setFrom(this.from);
        emailTemplate.setTo(subscriptionRequest.getEmail());
        emailTemplate.setSubject(this.subject);
        emailTemplate.setBody(this.createBody(subscriptionRequest));
        logger.info("Email Sent: "+ JsonUtil.stringify(emailTemplate));
        return emailTemplate;
    }
    
    /**
     * Create the email body
     * @param subscriptionRequest
     * @return
     */
    private String createBody(SubscriptionRequest subscriptionRequest){
        String body = this.bodyTemplate.replace("{userName}", subscriptionRequest.getFirstName());
        return body;
    }
}
