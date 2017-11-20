package server;

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
	
	public Medication(String medication, int dosage, int pid) {
		this.name = medication;
		this.dosage = dosage;
		this.patient_id = pid;
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
