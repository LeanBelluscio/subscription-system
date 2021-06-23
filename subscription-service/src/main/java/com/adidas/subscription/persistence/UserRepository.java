package com.adidas.subscription.persistence;

import java.util.Optional;
import com.adidas.subscription.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lbelluscio
 */
public interface UserRepository extends JpaRepository<User, Long>{
 
   Optional<User>  findByEmail(String email);
}
