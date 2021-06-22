package com.adidas.subscription.persistence;

import java.util.Optional;

import com.adidas.subscription.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
 
   Optional<User>  findByEmail(String email);
}
