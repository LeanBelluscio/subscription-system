package com.adidas.subscription.dto;

import java.util.Date;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author lbelluscio
 */
public class SubscriptionRequest extends Request{
 
    @NotEmpty(message = "Email is mandatory")
    @Email(message = "Email is not a valid address")
    private String email;

    private String firstName;

    @Pattern(regexp = "(?:[\\s]|^)(F|M|PNS)(?=[\\s]|$)|^$|^null", message = "Allowed values for gender: M, F, PNS or null")
    private String gender;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    @NotNull(message = "Birth Date is mandatory")
    private Date birthDate;

    @NotNull(message = "Consent is mandatory")
    @AssertTrue(message = "Consent should be true")
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
