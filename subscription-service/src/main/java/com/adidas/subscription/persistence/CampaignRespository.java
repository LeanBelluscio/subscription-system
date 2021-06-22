package com.adidas.subscription.persistence;

import com.adidas.subscription.domain.Campaign;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignRespository extends JpaRepository<Campaign, Long>{
    
}
