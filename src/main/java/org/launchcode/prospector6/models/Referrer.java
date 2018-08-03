package org.launchcode.prospector6.models;


import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Referrer {
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=2, max=30)
    @Pattern(regexp = "^[a-zA-Z]$")
    private String referrerLast;

    @NotNull
    @Size(min=2, max=30)
    @Pattern(regexp = "^[a-zA-Z]$")
    private String referrerFirst;

    @Pattern(regexp = "^[a-zA-Z0-9]+$")
    private String referrerAddress;
    private String referrerCity;
    private String referrerState;

    @Pattern(regexp="^\\d{5}(-\\d{4})?$")
    private String referrerZip;

    @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$")
    private String referrerPhone;

    private boolean isSelected;

    @Email
    @Column(unique = true)
    @Pattern(regexp="^([a-zA-Z0-9\\-\\.\\_]+)'+'(\\@)([a-zA-Z0-9\\-\\.]+)'+'(\\.)([a-zA-Z]{2,4})$", message="{invalid.email}")
    private String referrerEmail;


    @ManyToOne
    private User user;

    @OneToMany
    @JoinColumn(name = "referrer_id")
    private List<Prospect> prospects = new ArrayList<>();

    public Referrer(String referrerLast, String referrerFirst, String referrerAddress, String referrerCity, String referrerState, String referrerZip, String referrerPhone, String referrerEmail){
        this.referrerLast = referrerLast;
        this.referrerFirst = referrerFirst;
        this.referrerAddress = referrerAddress;
        this.referrerCity = referrerCity;
        this.referrerState = referrerState;
        this.referrerZip = referrerZip;
        this.referrerPhone = referrerPhone;
        this.referrerEmail = referrerEmail;
    }

    public Referrer(){}

    public int getId() {
        return id;
    }

    public void setId (int id) { this.id = id; }

    public String getReferrerLast() {
        return referrerLast;
    }

    public void setReferrerLast(String referrerLast) {
        this.referrerLast = referrerLast;
    }

    public String getReferrerFirst() {
        return referrerFirst;
    }

    public void setReferrerFirst(String referrerFirst) {
        this.referrerFirst = referrerFirst;
    }

    public String toString(){
        if (this.referrerLast == null) {
            return null;
        }
        if (this.referrerFirst != null){
            return this.referrerLast + ", " + this.referrerFirst;
        }
        else {
            return this.referrerLast;
        }
    }

    public String getReferrerAddress() {
        return referrerAddress;
    }

    public void setReferrerAddress(String referrerAddress) {
        this.referrerAddress = referrerAddress;
    }

    public String getReferrerCity() {
        return referrerCity;
    }

    public void setReferrerCity(String referrerCity) {
        this.referrerCity = referrerCity;
    }

    public String getReferrerState() {
        return referrerState;
    }

    public void setReferrerState(String referrerState) {
        this.referrerState = referrerState;
    }

    public String getReferrerZip() {
        return referrerZip;
    }

    public void setReferrerZip(String referrerZip) {
        this.referrerZip = referrerZip;
    }

    public String getReferrerPhone() {
        return referrerPhone;
    }

    public void setReferrerPhone(String referrerPhone) {
        this.referrerPhone = referrerPhone;
    }

    public String getReferrerEmail() {
        return referrerEmail;
    }

    public void setReferrerEmail(String referrerEmail) {
        this.referrerEmail = referrerEmail;
    }

    public User getUser() {
        return user;
    }

    public void setUser (User user) {
        this.user = user;
    }

    public boolean isSelected(Integer referrerId) {
        if (referrerId !=null) {
            return referrerId.equals(id);
        }
        return false;
    }

    public List<Prospect> getProspects() {return prospects;}

}
