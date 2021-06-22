package com.adidas.subscription.dto;

import java.util.Date;

import com.adidas.subscription.domain.Subscription;
import com.adidas.subscription.domain.User;
import com.fasterxml.jackson.annotation.JsonFormat;

public class SubscriptionResponse {
    
    private Long subscriptionId;
    
    private User user;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    private Date subscriptionDate;

    private CampaignData campaign;


    public void populate(Subscription subscription){
        this.subscriptionId = subscription.getId();
        this.user = subscription.getUser();
        CampaignData campaignData = new CampaignData();
        campaignData.populate(subscription.getCampaign());
        this.campaign = campaignData;

    }

    public Long getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(Long subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public CampaignData getCampaign() {
        return campaign;
    }

    public void setCampaign(CampaignData campaign) {
        this.campaign = campaign;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
