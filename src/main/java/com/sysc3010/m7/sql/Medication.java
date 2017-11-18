package com.sysc3010.m7.sql;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Medication {
	String name;
	int dosage;
	int patient_id;
	int id;
	
	public Medication(ResultSet r) throws SQLException {
		this.name = r.getString("medication");
		this.dosage = r.getInt("dosage");
		this.id = r.getInt("id");
		this.patient_id = r.getInt("patient_id");
	}
	
	public Medication() {
		
	}
	
	public String toString() {
		return " " + this.id + "    " + this.patient_id + "    " + this.name + "    " + this.dosage;
	}
}
