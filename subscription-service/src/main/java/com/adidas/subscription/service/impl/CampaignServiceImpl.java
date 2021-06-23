package com.adidas.subscription.service.impl;

import java.util.List;
import java.util.Optional;

import com.adidas.subscription.domain.Campaign;
import com.adidas.subscription.errorhandling.ObjectNotFoundException;
import com.adidas.subscription.persistence.CampaignRespository;
import com.adidas.subscription.service.CampaignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author lbelluscio
 */
@Component
public class CampaignServiceImpl implements CampaignService{


    private static final Logger logger = LoggerFactory.getLogger(CampaignServiceImpl.class);

    @Autowired
    private CampaignRespository campaignRespository;

    @Override
    public List<Campaign> findAll() {
       
        return this.campaignRespository.findAll();
    }

    @Override
    public Optional<Campaign> findById(Long id) {
        logger.info("Getting Campaign with Id: "+ id);
        Optional<Campaign> campaignOp = this.campaignRespository.findById(id);
        if(campaignOp.isPresent()){
            return campaignOp;
        }else{
            throw new ObjectNotFoundException("Campaing with ID: "+ id + " could not be found");
        }
       
    }

    @Override
    public Campaign save(Campaign campaign) {

        return this.campaignRespository.save(campaign);
    }
    
}
