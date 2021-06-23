package com.adidas.publication.controller;

import com.adidas.publication.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lbelluscio
 * This controller returns an access token for development/testing purpose
 */
@RestController
@RequestMapping("/publication/api/devops")
public class AuthenticationController {
    
    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/auth")
    public ResponseEntity<String> auth() {

        String token = jwtUtil.getJWTToken();
        return ResponseEntity.ok(token);

    }
}
