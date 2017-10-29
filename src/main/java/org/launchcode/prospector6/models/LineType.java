package org.launchcode.prospector6.models;

public enum LineType {
    COMMERCIAL ("Commercial"),
    PERSONAL ("Personal");

    private final String name;

    LineType(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
