package com.adidas.subscription.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author lbelluscio
 */
public class SubscriptionRequest extends Request{
 
    @NotEmpty(message = "Email is mandatory")
    private String email;

    private String firstName;

    private String gender;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    @NotNull(message = "Birth Date is mandatory")
    private Date birthDate;

    @NotNull(message = "Consent is mandatory")
    private Boolean consent;

    @NotNull(message = "Campaign ID is mandatory")
    private Long campaignId;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Boolean getConsent() {
        return consent;
    }

    public void setConsent(Boolean consent) {
        this.consent = consent;
    }

    public Long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }

    
}
