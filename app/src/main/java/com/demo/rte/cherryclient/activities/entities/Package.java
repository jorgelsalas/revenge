package com.demo.rte.cherryclient.activities.entities;

/**
 * Created by Jorge on 02/08/2015. Package abstraction
 */
public class Package {

    private String name;
    private String acronym;

    public Package(String name, String acronym){
        this.setName(name);
        this.setAcronym(acronym);
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
