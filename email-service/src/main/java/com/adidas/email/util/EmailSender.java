package com.adidas.email.util;

import java.util.Properties;

import com.adidas.email.dto.SubscriptionRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {
    
    @Value("${email.inbox.host}")
    private String host;

    @Value("${email.inbox.port}")
    private int port;

    @Value("${email.inbox.tls}")
    private String tls;

    @Value("${email.inbox.user}")
    private String user;

    @Value("${email.inbox.password}")
    private String password;

    @Value("${email.inbox.from}")
    private String from;

    public void sendEmail(SubscriptionRequest request){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(this.host);
        mailSender.setPort(this.port);
        
        mailSender.setUsername(this.user);
        mailSender.setPassword(this.password);
        
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", this.tls);

        SimpleMailMessage message = new SimpleMailMessage(); 
        message.setFrom(this.from);
        message.setTo(request.getEmail()); 
        message.setSubject("Subscription"); 
        message.setText("Text");

         mailSender.send(message);
    }
}
