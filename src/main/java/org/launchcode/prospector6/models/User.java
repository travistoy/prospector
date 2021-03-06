package org.launchcode.prospector6.models;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Entity
public class User {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Column(unique = true)
    //@Size(min=6, max=12)
    @Pattern(regexp = "^[a-zA-Z0-9]+$")
    private String username;

    @NotNull
    @Size(min=2, max=30)
    private String userLast;

    @NotNull
    @Size(min=2, max=30)
    private String userFirst;

    @NotNull
    //@Size(min=5, max=12)
    private String password;

    @Transient
    private String verify;

    @Email
    @Column(unique = true)
    private String email;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Prospect> prospects = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Referrer> referrers = new ArrayList<>();

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="user_roles",
            joinColumns={@JoinColumn(name="user_id", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="role_id", referencedColumnName="id")})
    private Collection< Role > roles;

    private boolean enabled;

    public User(String username, String userLast, String userFirst, String password, String email, boolean enabled){
        this.username = username;
        this.userLast = userLast;
        this.userFirst = userFirst;
        this.password = password;
        this.email = email;
        this.enabled = enabled;
    }

    public User(){}

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public boolean isEnabled() { this.enabled = enabled;
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Prospect> getProspects() {return prospects;}

    public List<Referrer> getReferrers() {return referrers;}

    public Collection < Role > getRoles() {
        return roles;
    }

    public void setRoles(Collection < Role > roles) {
        this.roles = roles;
    }


}
