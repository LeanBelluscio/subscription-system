package com.adidas.subscription.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author lbelluscio
 */
@Entity(name="campaign")
@Table(name="campaign")
public class Campaign {
    
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="campaign_name")
    private String campaignName;

    @Column(name="description")
    private String description;

    @Column(name="start_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    private Date startDate;

    @Column(name="end_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    private Date endDate;


    
    /**
     * Default constructor
     */
    public Campaign() {
        super();
    }
    
    /**
     * 
     * @param campaignName
     * @param description
     * @param startDate
     * @param endDate
     */
    public Campaign(String campaignName, String description, Date startDate, Date endDate) {
        this.campaignName = campaignName;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Full constructor
     * @param id
     * @param campaignName
     * @param description
     * @param startDate
     * @param endDate
     */
    public Campaign(Long id, String campaignName, String description, Date startDate, Date endDate) {
        this.id = id;
        this.campaignName = campaignName;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((campaignName == null) ? 0 : campaignName.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
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
        Campaign other = (Campaign) obj;
        if (campaignName == null) {
            if (other.campaignName != null)
                return false;
        } else if (!campaignName.equals(other.campaignName))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (endDate == null) {
            if (other.endDate != null)
                return false;
        } else if (!endDate.equals(other.endDate))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (startDate == null) {
            if (other.startDate != null)
                return false;
        } else if (!startDate.equals(other.startDate))
            return false;
        return true;
    }

    
}
