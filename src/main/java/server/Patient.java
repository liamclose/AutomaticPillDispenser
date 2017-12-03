package server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Patient {
    String name;
    int room;
    int id;
    ArrayList<Medication> medications;

    public Patient(ResultSet r) throws SQLException {
        this.name = r.getString("patient_name");
        this.room = r.getInt("room_number");
        this.id = r.getInt("id");
    }

    public Patient(String name, int room) {
        this.name = name;
        this.room = room;
    }

    public String toString() {
        return this.id + "    " + this.name + "    " + this.room;
    }
    public String getName() {
        return this.name;
    }

    public int getRoom() {
        return this.room;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Medication> getMedications() {
        return medications;
    }

    public void setMedications(ArrayList<Medication> medications) {
        this.medications = medications;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRoom(int room) {
        this.room = room;
    }
    
    
    
}