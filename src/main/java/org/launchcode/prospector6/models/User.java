package org.launchcode.prospector6.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
}