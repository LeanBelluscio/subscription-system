package com.adidas.subscription.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity(name="subscription")
@Table(name="subscription")
public class Subscription {
    
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name="campaign_id", nullable=false)
    private Campaign campaign;

    @Column(name="subscription_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    private Date subscriptionDate;



    public Subscription() {
        super();
    }

    /**
     * 
     * @param user
     * @param campaign
     * @param subscriptionDate
     */
    public Subscription(User user, Campaign campaign, Date subscriptionDate) {
        this.user = user;
        this.campaign = campaign;
        this.subscriptionDate = subscriptionDate;
    }

    /**
     * Full constructor
     * @param id
     * @param user
     * @param campaign
     * @param subscriptionDate
     */
    public Subscription(Long id, User user, Campaign campaign, Date subscriptionDate) {
        this.id = id;
        this.user = user;
        this.campaign = campaign;
        this.subscriptionDate = subscriptionDate;
    }


    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((campaign == null) ? 0 : campaign.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((subscriptionDate == null) ? 0 : subscriptionDate.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Subscription other = (Subscription) obj;
        if (campaign == null) {
            if (other.campaign != null)
                return false;
        } else if (!campaign.equals(other.campaign))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (subscriptionDate == null) {
            if (other.subscriptionDate != null)
                return false;
        } else if (!subscriptionDate.equals(other.subscriptionDate))
            return false;
        if (user == null) {
            if (other.user != null)
                return false;
        } else if (!user.equals(other.user))
            return false;
        return true;
    }

    

}
