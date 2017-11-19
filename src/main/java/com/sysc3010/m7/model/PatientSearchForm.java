package com.sysc3010.m7.model;

public class PatientSearchForm {
    private String id;

    public PatientSearchForm() {

    }

    public PatientSearchForm(String name) {
        this.id = name;
    }

    public String getName() {
        return id;
    }

    public void setName(String name) {
        this.id = name;
    }

}
