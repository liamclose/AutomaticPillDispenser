package server;

import java.sql.ResultSet;
import java.sql.SQLException;


public class Medication {
	String name;
	int dosage;
	int patient_id;
	int id;
	String time;
	
	
	public Medication(ResultSet r) throws SQLException {
		this.name = r.getString("medication");
		this.dosage = r.getInt("dosage");
		this.id = r.getInt("id");
		this.patient_id = r.getInt("patient_id");
		this.time = r.getDate("time").toString();
	}
	
	public Medication(String medication, int dosage, int pid, String time) {
		this.name = medication;
		this.dosage = dosage;
		this.patient_id = pid;
		this.time = time;
	}
	
	public String toString() {
		return " " + this.id + "    " + this.patient_id + "    " + this.name + "    " + this.dosage;
	}
	
	public int getID() {
		return this.id;
	}
	public int getPatientID() {
		return this.patient_id;
	}
	public int getDosage() {
		return this.id;
	}
}
