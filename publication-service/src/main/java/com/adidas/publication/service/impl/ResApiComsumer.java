package com.adidas.publication.service.impl;

import java.net.URI;

import com.adidas.publication.dto.Request;
import com.adidas.publication.dto.SubscriptionRequest;
import com.adidas.publication.util.JsonUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public abstract class ResApiComsumer {
    

    private static final Logger logger = LoggerFactory.getLogger(ResApiComsumer.class);

    private RestTemplate restTemplate = new RestTemplate();

    protected String basePath;

    public ResponseEntity<String> post(Request request, String endpoint) throws Exception{
        
        try{
            URI uri = new URI(this.basePath+endpoint);
            logger.info("Calling External API.: "+ uri.getPath() +  System.lineSeparator() + JsonUtil.stringify(request));
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("API-KEY", "secret");
            HttpEntity<Request> httpEntity = new HttpEntity<Request>(request,headers);
            
            ResponseEntity<String> response = restTemplate.postForEntity(uri,httpEntity, String.class);
            if(response.getStatusCode().equals(HttpStatus.ACCEPTED)){
                return restTemplate.postForEntity(uri,httpEntity, String.class);
            }else{
                throw new Exception("Api "+ uri.getPath()+ "Call Error. Status Code:" + response.getStatusCode().value());
            }
        }catch(Exception e){
            throw new Exception("Api Call Error");
        }
    }
}
