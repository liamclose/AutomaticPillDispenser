package com.sysc3010.m7.model;

import java.sql.Date;

public class NewPatientForm {
    private String name;
    private int room;
    private int id;
    private Date time;

    public NewPatientForm() {
    }
    public NewPatientForm(String name, int room, int id, Date time) {
        super();
        this.name = name;
        this.room = room;
        this.id = id;
        this.time = time;
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

}
