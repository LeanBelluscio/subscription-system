package com.adidas.subscription.persistence;

import java.util.Optional;
import com.adidas.subscription.domain.Campaign;
import com.adidas.subscription.domain.Subscription;
import com.adidas.subscription.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lbelluscio
 */
public interface SubscriptionRepository extends JpaRepository<Subscription,Long>{

    Optional<Subscription> findByUserAndCampaign(User user, Campaign campaign);
}
