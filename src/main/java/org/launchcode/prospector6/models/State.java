package org.launchcode.prospector6.models;

public enum State {
    AK ("AK"),
    AL ("AL"),
    AR ("AR"),
    AZ ("AZ"),
    CA ("CA"),
    CO ("CO"),
    CT ("CT"),
    DC ("DC"),
    DE ("DE"),
    FL ("FL"),
    GA ("GA"),
    HI ("HI"),
    IA ("IA"),
    ID ("ID"),
    IL ("IL"),
    IN ("IN"),
    KS ("KS"),
    KY ("KY"),
    LA ("LA"),
    MA ("MA"),
    ME ("ME"),
    MD ("MD"),
    MI ("MI"),
    MN ("MN"),
    MO ("MO"),
    MS ("MS"),
    MT ("MT"),
    NC ("NC"),
    ND ("ND"),
    NE ("NE"),
    NH ("NH"),
    NJ ("NJ"),
    NM ("NM"),
    NV ("NV"),
    NY ("NY"),
    OH ("OH"),
    OK ("OK"),
    OR ("OR"),
    PA ("PA"),
    PR ("PR"),
    RI ("RI"),
    SC ("SC"),
    SD ("SD"),
    TN ("TN"),
    TX ("TX"),
    UT ("UT"),
    VA ("VA"),
    VT ("VT"),
    WA ("WA"),
    WI ("WI"),
    WV ("WV"),
    WY ("WY");

    private final String name;

    State(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
