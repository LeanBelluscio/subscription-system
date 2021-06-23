package com.adidas.publication.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

    private final String PREFIX = "Bearer ";

    @Value("${security.secret}")
    private String secretKey;

    @Value("${security.user}")
    private String user;


    public JwtUtil(){
    }


    public String getJWTToken() {
        List authorities = new ArrayList();
        authorities.add("PUBLICATION_USER");
        String token = Jwts.builder().setId("adidasJWT").setSubject(user)
                .claim("authorities",
                authorities)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 6000000))
                .signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();

        return PREFIX + token;
    }
}
