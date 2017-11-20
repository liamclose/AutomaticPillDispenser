package com.sysc3010.m7.sql;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Patient {
    private String name;
    private int room;
    private int id;
    private Date dispenseTme;
    private ArrayList<Medication> medications;

    public Patient(ResultSet r) throws SQLException {
        this.name = r.getString("patient_name");
        this.room = r.getInt("room_number");
        this.id = r.getInt("id");
    }

    public Patient(String name, int room) {
        this.name = name;
        this.room = room;
    }

    public void addMedication(Medication newMed) {
        medications.add(newMed);
    }

    public String toString() {
        return " " + this.id + "    " + this.name + "    " + this.room;
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

    public Date getDispenseTme() {
        return dispenseTme;
    }

    public void setDispenseTme(Date time) {
        this.dispenseTme = time;
    }

    public ArrayList<Medication> getMedications() {
        return medications;
    }

    public void setMedications(ArrayList<Medication> medications) {
        this.medications = medications;
    }

}
