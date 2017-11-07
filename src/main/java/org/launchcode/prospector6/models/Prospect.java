package org.launchcode.prospector6.models;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Entity
public class Prospect {
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=2, max=30)
    private String name;

    private String firstName;
    private String address;
    private String city;
    private State state;
    private int zip;
    private String phone;
    private String email;
    private LineType type;
    private double premium;
    private double commissionRate;
    private double commission;
    private long quotedFromCreated;
    private long soldFromCreated;
    private long soldFromQuoted;
    private boolean thankYou;


    @DateTimeFormat
    private LocalDate created;
    private LocalDate quoteDate;
    private LocalDate soldDate;

    @ManyToOne
    private Referrer referrer;


    public Prospect(String name, String firstName, String address, String city, State state, int zip, String phone, String email, LocalDate created, LineType type, LocalDate quoteDate, double premium, double commissionRate, LocalDate soldDate, boolean thankYou){
        this.name = name;
        this.firstName = firstName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone = phone;
        this.email = email;
        this.created = created;
        this.type = type;
        this.quoteDate = quoteDate;
        this.premium = premium;
        this.commissionRate = commissionRate;
        this.commission = commission;
        this.soldDate = soldDate;
        this.thankYou = thankYou;
    }

    public Prospect(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String toString(){
        if (this.firstName != null){
            return this.name + ", " + this.firstName;
        }
        else {
            return this.name;
        }
    }


    public LocalDate getCreated() { return created; }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public LocalDate getQuoteDate() {
        return quoteDate;
    }

    public void setQuoteDate(LocalDate quoteDate) {
        this.quoteDate = quoteDate;
    }

    public LocalDate getSoldDate() {
        return soldDate;
    }

    public void setSoldDate(LocalDate soldDate) {
        this.soldDate = soldDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getPremium() {
        return premium;
    }

    public void setPremium(double premium) {
        this.premium = premium;
    }

    public double getCommissionRate() { this.commissionRate = commissionRate;
        return commissionRate;
    }

    public void setCommissionRate(double commissionRate) {
        this.commissionRate = commissionRate;
    }

    public double getCommission() { this.commission = this.premium * this.commissionRate;
        this.commission = BigDecimal.valueOf(this.commission).setScale(2, RoundingMode.HALF_UP).doubleValue();
        return commission; }

    public void setCommission(double commission) { this.commission = commission; }

    public Referrer getReferrer() {
        return referrer;
    }

    public void setReferrer(Referrer referrer) {
        this.referrer = referrer;
    }

    public LineType getType() {
        return type;
    }

    public void setType(LineType type) {
        this.type = type;
    }

    public boolean isThankYou() {
        return thankYou;
    }

    public void setThankYou(boolean thankYou) {
        this.thankYou = thankYou;
    }
}
