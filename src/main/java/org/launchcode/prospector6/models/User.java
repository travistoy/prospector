package org.launchcode.prospector6.models;

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
    @Size(min=2, max=30)
    private String userLast;

    @NotNull
    @Size(min=2, max=30)
    private String userFirst;

    private int totalProspects;
    private int totalQuoted;
    private int totalSold;
    private double totalCommission;
    private double totalPremium;

    //@Autowired
    //private Prospect prospect;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Prospect> prospects = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Referrer> referrers = new ArrayList<>();

    public User(){
        this.userLast = userLast;
        this.userFirst = userFirst;
        this.totalProspects = totalProspects;
        this.totalQuoted = totalQuoted;
        this.totalSold = totalSold;
        this.totalCommission = totalCommission;
        this.totalPremium = totalPremium;
    }


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserLast() {
        return this.userLast;
    }

    public void setUserLast(String last) {
        this.userLast = last;
    }

    public String getUserFirst() {
        return this.userFirst;
    }

    public void setUserFirst(String first) {
        this.userFirst = first;
    }

    public String toString(){
        return String.format("%s %s",this.userFirst, this.userLast);
    }

    public List<Prospect> getProspects() {return prospects;}

    public List<Referrer> getReferrers() {return referrers;}


   /* public Prospect getProspect(){
        return prospect;
    }



        public int getTotalProspects(Prospect prospect) { for (Prospect : prospects)
            { this.totalProspects = this.totalProspects + 1; }
            return totalProspects;
        }

        public void setTotalProspects(int totalProspects) {
            this.totalProspects = totalProspects;
        }

        public int getQuotedProspects(Prospect prospect) {
            for (Prospect a : prospects) {
                if (a.getQuoteDate() != null) {
                    this.totalQuoted++;
                }
            }
            return totalQuoted;
        }

        public void setQuotedProspects(int totalProspects) {
            this.totalQuoted = totalQuoted;
        }

        public int getSoldProspects() {
            for (Prospect a : prospects) {
                a.getSoldDate();
                if (a.getSoldDate() != null) {
                    this.totalSold++;
                }
            }
            return totalSold;
        }

        public void setSoldProspects(int totalSold) {
            this.totalSold = totalSold;
        }

        public double getTotalCommission(Prospect prospect) {
            for (Prospect a : prospects) {
                a.getCommission();
                if (a.getCommission() != 0) {
                    this.totalCommission = this.totalCommission + a.getCommission();
                }
            }
            return totalCommission;
        }

        public void setTotalCommission(double totalCommission) {
            this.totalCommission = totalCommission;
        }

        public double getTotalPremium(Prospect prospect) {
            for (Prospect a : prospects) {
                a.getPremium();
                if (a.getPremium() != 0) {
                    this.totalPremium = this.totalPremium + a.getPremium();
                }
            }
            return totalPremium;
        }

        public void setTotalPremium(double totalPremium) {
            this.totalPremium = totalPremium;
        }
    */
}
