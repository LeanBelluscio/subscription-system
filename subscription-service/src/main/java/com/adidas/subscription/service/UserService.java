package com.adidas.subscription.service;

import java.util.Optional;

import com.adidas.subscription.domain.User;

public interface UserService {
    
    Optional<User> findByEmail(String email);

    User saveUser(User user);
}
