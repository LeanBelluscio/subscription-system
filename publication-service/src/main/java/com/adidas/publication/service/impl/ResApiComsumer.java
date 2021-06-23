package com.adidas.publication.service.impl;

import java.net.URI;
import java.util.Collections;

import com.adidas.publication.dto.Request;
import com.adidas.publication.util.JsonUtil;
import com.adidas.publication.util.JwtUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public abstract class ResApiComsumer {
    

    private static final Logger logger = LoggerFactory.getLogger(ResApiComsumer.class);

    private RestTemplate restTemplate = new RestTemplate();

    private final String HEADER = "Authorization";

    @Autowired
    private JwtUtil jwtUtil;

    protected String basePath;

    public ResponseEntity<String> post(Request request, String endpoint) throws Exception{
        
        try{
            URI uri = new URI(this.basePath+endpoint);
            logger.info("Calling External API.: "+ this.basePath+endpoint +  System.lineSeparator() + JsonUtil.stringify(request));
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            this.setAuthorizationHeader(headers);
            HttpEntity<String> httpEntity = new HttpEntity<String>(JsonUtil.stringify(request),headers);
            ResponseEntity<String> response = restTemplate.postForEntity(uri,httpEntity, String.class);
            if(response.getStatusCode().equals(HttpStatus.OK)){
                logger.debug("Subscrition Sent: " + JsonUtil.stringify(httpEntity.getBody()));
                return response;
            }else{
                throw new Exception("Api "+ uri.getPath()+ "Call Error. Status Code:" + response.getStatusCode().value());
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception("Api Call Error:"+e.getMessage());
        }
    }

    private void setAuthorizationHeader(HttpHeaders headers){
        headers.set(HEADER, jwtUtil.getJWTToken());
    }
}
