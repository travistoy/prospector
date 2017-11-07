package org.launchcode.prospector6.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    private String referrerLast;

    @NotNull
    @Size(min=2, max=30)
    private String referrerFirst;

    private String referrerAddress;
    private String referrerCity;
    private String referrerState;
    private int referrerZip;
    private String referrerPhone;
    private String referrerEmail;

    @OneToMany
    @JoinColumn(name = "referrer_id")
    private List<Prospect> prospects = new ArrayList<>();


    public Referrer(String referrerLast, String referrerFirst, String referrerAddress, String referrerCity, String referrerState, int referrerZip, String referrerPhone, String referrerEmail){
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

    public void setId(int id) {
        this.id = id;
    }

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

    public int getReferrerZip() {
        return referrerZip;
    }

    public void setReferrerZip(int referrerZip) {
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

    public List<Prospect> getProspects() {return prospects;}

}
