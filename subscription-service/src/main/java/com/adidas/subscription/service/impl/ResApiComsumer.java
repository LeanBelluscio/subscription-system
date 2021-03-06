package com.adidas.subscription.service.impl;

import java.net.URI;
import java.util.Collections;

import com.adidas.subscription.dto.Request;
import com.adidas.subscription.util.JsonUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author lbelluscio
 */
@Component
public abstract class  ResApiComsumer {
    
    private static final Logger logger = LoggerFactory.getLogger(ResApiComsumer.class);

    private RestTemplate restTemplate = new RestTemplate();

    protected String basePath;

    public ResponseEntity<String> post(Request request, String endpoint, String accessToken) throws Exception{
        
        try{
            URI uri = new URI(this.basePath+endpoint);
            logger.info("Calling External API.: "+ uri.getPath() +  System.lineSeparator() + JsonUtil.stringify(request));
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.set("Authorization", accessToken);
            HttpEntity<String> httpEntity = new HttpEntity<String>(JsonUtil.stringify(request),headers);
            ResponseEntity<String> response = restTemplate.postForEntity(uri,httpEntity, String.class);
            if(response.getStatusCode().equals(HttpStatus.OK)){
                return response;
            }else{
                throw new Exception("Api "+ uri.getPath()+ "Call Error. Status Code:" + response.getStatusCode().value());
            }
        }catch(Exception e){
            throw new Exception("Api Call Error");
        }
    }
}
