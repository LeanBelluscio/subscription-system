package com.adidas.subscription.service.impl;

import java.util.Optional;

import com.adidas.subscription.domain.User;
import com.adidas.subscription.persistence.UserRepository;
import com.adidas.subscription.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService{

    @Autowired
    CacheManager cacheManager;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Cacheable(value = "users")
    public Optional<User> findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    @Override
    public User saveUser(User user) {
        cacheManager.getCache("users").invalidate();
        return this.userRepository.save(user);
    }
    
}
