package com.sysc3010.m7.model;

import java.sql.Date;

public class NewPatientForm {
    private String name;
    private int room;
    private int id;
    private String time;

    private String medName;
    private int dosage;
    private int medId;

    public NewPatientForm() {
    }

    public NewPatientForm(String name, int room, int id, String time, String medName, int dosage, int medId) {
        super();
        this.name = name;
        this.room = room;
        this.id = id;
        this.time = time;
        this.medName = medName;
        this.dosage = dosage;
        this.medId = medId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMedName() {
        return medName;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public int getDosage() {
        return dosage;
    }

    public void setDosage(int dosage) {
        this.dosage = dosage;
    }

    public int getMedId() {
        return medId;
    }

    public void setMedId(int medId) {
        this.medId = medId;
    }
}
