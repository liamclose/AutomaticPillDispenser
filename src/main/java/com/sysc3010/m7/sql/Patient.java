package com.sysc3010.m7.sql;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Patient {
	String name;
	int room;
	int id;
	
	public Patient(ResultSet r) throws SQLException {
		this.name = r.getString("patient_name");
		this.room = r.getInt("room_number");
		this.id = r.getInt("id");
	}
	
	public String toString() {
		return " " + this.id + "    " + this.name + "    " + this.room;
	}
}
