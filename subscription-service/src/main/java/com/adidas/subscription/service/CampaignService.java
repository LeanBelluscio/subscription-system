package com.adidas.subscription.service;

import java.util.List;
import java.util.Optional;

import com.adidas.subscription.domain.Campaign;

/**
 * @author lbelluscio
 */
public interface CampaignService {
    
    Optional<Campaign> findById(Long id);

    List<Campaign> findAll();

    Campaign save(Campaign campaign);
}
