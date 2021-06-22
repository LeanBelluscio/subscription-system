package com.adidas.subscription.dto;

import com.adidas.subscription.domain.Campaign;

public class CampaignData {
    
    private Long id;

    private String campaignName;

    public void populate(Campaign campaign){
        this.id = campaign.getId();
        this.campaignName = campaign.getCampaignName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    
}
