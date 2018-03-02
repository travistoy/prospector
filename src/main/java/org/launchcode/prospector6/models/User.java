package org.launchcode.prospector6.models;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dcannarozzi on 10/28/17.
 */
@Entity
public class User {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=6, max=12)
    private String userName;

    @NotNull
    @Size(min=2, max=30)
    private String userLast;

    @NotNull
    @Size(min=2, max=30)
    private String userFirst;

    @NotNull
    @Size(min=5, max=12)
    private String password;

    @Transient
    private String verify;

    @Email
    private String email;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Prospect> prospects = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Referrer> referrers = new ArrayList<>();

    public User(String userName, String userLast, String userFirst, String password, String email){
        this.userName = userName;
        this.userLast = userLast;
        this.userFirst = userFirst;
        this.password = password;
        this.email = email;
    }

    public User(){}

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLast() {
        return this.userLast;
    }

    public void setUserLast(String userLast) {
        this.userLast = userLast;
    }

    public String getUserFirst() {
        return this.userFirst;
    }

    public void setUserFirst(String userFirst) {
        this.userFirst = userFirst;
    }

    public String toString(){
        return String.format("%s %s",this.userFirst, this.userLast);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerify() {
        return verify;
    }

    public void setVerify(String verify) {
        this.verify = verify;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Prospect> getProspects() {return prospects;}

    public List<Referrer> getReferrers() {return referrers;}


}
