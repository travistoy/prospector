package org.launchcode.prospector6.models;


import org.hibernate.validator.constraints.Email;
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
import java.time.temporal.ChronoUnit;

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

    private String zip;

    private String phone;

    @Email
    private String email;

    private LineType type;
    private double premium;
    private int commissionRate;
    private double commission;
    private long quotedFromCreated;
    private long soldFromCreated;
    private long soldFromQuoted;
    private boolean thankYou;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate created;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate quoteDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate soldDate;

    @ManyToOne
    private Referrer referrer;

    @ManyToOne
    private User user;

    public Prospect(String name, String firstName, String address, String city, State state, String zip, String phone, String email, LineType type, LocalDate quoteDate, double premium, int commissionRate, LocalDate soldDate ){

        this.name = name;
        this.firstName = firstName;
        this.created = created;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone = phone;
        this.email = email;
        this.type = type;
        this.quoteDate = quoteDate;
        this.premium = premium;
        this.commissionRate = commissionRate;
        this.soldDate = soldDate;
        this.quotedFromCreated = quotedFromCreated;
        this.soldFromCreated = soldFromCreated;
        this.soldFromQuoted = soldFromQuoted;

    }

    public Prospect(){}

    public int getId() {
        return id;
    }

    public void setId (int id) { this.id = id; }

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


    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public LocalDate getQuoteDate() {
        return quoteDate;
    }

    public void setQuoteDate(LocalDate quoteDate) {
        if (quoteDate != null){
            if (quoteDate.isBefore (this.created)){
                throw new IllegalArgumentException("Date needs to be after creation date");
            }
        }
        this.quoteDate = quoteDate;
    }

    public LocalDate getSoldDate() {
        return soldDate;
    }

    public void setSoldDate(LocalDate soldDate) {
        if (soldDate != null) {
            if (soldDate.isBefore(quoteDate)) {
                throw new IllegalArgumentException("Date needs to be on or after quote date");
            }
        }
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

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
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

    public int getCommissionRate() { this.commissionRate = commissionRate;
        return commissionRate;
    }

    public void setCommissionRate(int commissionRate) {
        this.commissionRate = commissionRate;
    }

    public double getCommission() { this.commission = premium * commissionRate/100;
        this.commission = BigDecimal.valueOf(this.commission).setScale(2, RoundingMode.HALF_UP).doubleValue();
        return commission; }

    public void setCommission(double commission) { this.commission = commission; }

    public Referrer getReferrer() {
        return referrer;
    }

    public void setReferrer(Referrer referrer) {
        this.referrer = referrer;
    }

    public User getUser() {
        return user;
    }

    public void setUser (User user) {
        this.user = user;
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

    public long getQuotedFromCreated() {
        if (quoteDate == null) {
            this.quotedFromCreated = 0;
        }
        else {
            this.quotedFromCreated = ChronoUnit.DAYS.between(this.created, this.quoteDate);
        }
        return quotedFromCreated;
    }

    public void setQuotedFromCreated(long quotedFromCreated) {
        this.quotedFromCreated = quotedFromCreated;
    }

    public long getSoldFromCreated() {
        if (soldDate == null){
            this.soldFromCreated = 0;
        }
        else {
            this.soldFromCreated = ChronoUnit.DAYS.between(this.created, this.soldDate);
        }
        return soldFromCreated;
    }

    public void setSoldFromCreated(long soldFromCreated) {
        this.soldFromCreated = soldFromCreated;
    }

    public long getSoldFromQuoted() {
        if (quoteDate == null || soldDate == null){
            this.soldFromQuoted = 0;
        }
        else {
            this.soldFromQuoted = ChronoUnit.DAYS.between(this.quoteDate, this.soldDate);
        }
        return soldFromQuoted;
    }

    public void setSoldFromQuoted(long soldFromQuoted) {this.soldFromQuoted = soldFromQuoted;}


}
