package com.sysc3010.m7.model;

public class PatientData {
    private String id;
    private String firstName;
    private String lastName;
    private int hour;
    private boolean halfHour;

    public PatientData() {

    }

    public PatientData(String id, String firstName, String lastName, int hour, boolean halfHour) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hour = hour;
        this.halfHour = halfHour;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public boolean isHalfHour() {
        return halfHour;
    }

    public void setHalfHour(boolean halfHour) {
        this.halfHour = halfHour;
    }
}
