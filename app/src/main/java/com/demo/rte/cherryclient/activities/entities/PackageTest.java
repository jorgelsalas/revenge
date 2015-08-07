package com.demo.rte.cherryclient.activities.entities;

/**
 * Created by Jorge on 06/08/2015.
 */
public class PackageTest {

    private String name;
    private String acronym;
    private String status;

    public final static String TEST_STATUS_NOT_STARTED = "not started";
    public final static String TEST_STATUS_IN_PROGRESS = "in progress";
    public final static String TEST_STATUS_FAILED = "failed";
    public final static String TEST_STATUS_SUCCESS = "success";

    public PackageTest(String name, String acronym, String status){
        this.setName(name);
        this.setAcronym(acronym);
        this.setStatus(status);
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
