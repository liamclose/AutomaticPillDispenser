package com.sysc3010.m7.sql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import server.Medication;
import server.Patient;

//@author Megan Ardron
public class Database{
	
	
	public static final String DATABASE = "jdbc:mysql://localhost:3306/dispenser";
	public static final String USER = "root";
	public static final String PASS = "password";
	protected Connection con;
	
	public Database() throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");  
		try {
			con=DriverManager.getConnection(DATABASE, USER, PASS);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/*
	 * Gets all patients from the database and returns them as an arraylist
	 */
	public ArrayList<Patient> getPatients() {
		try {
			ArrayList<Patient> pats = new ArrayList<Patient>();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM patient;");
			while(rs.next())  {
				Patient p = new Patient(rs);
				pats.add(p);
			}
			return pats;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	/*
	 * Insert a patient into the database.
	 */
	public void insertPatient(Patient p) {
		try {
			Statement stmt = con.createStatement();
			int i = stmt.executeUpdate("INSERT into patient (patient_name, room_number) VALUES ( '" + p.getName() + "' , " + p.getRoom() + ");");
			System.out.println(i);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Insert scheduled medication into the database.
	 */
	public void insertMedication(Medication med) {
		try {
			Statement stmt = con.createStatement();
			int i = stmt.executeUpdate("INSERT into medication (medication, dosage, patient_id, time) VALUES ( '" + med.getName() + "' , " + med.getDosage() + ", " + med.getPatient_id() + ", '" + med.getTime() + "');");
			System.out.println(i);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 
	 */
	public static String toSQLDateTime(java.util.Date date) {
		System.out.println(date.toString());
		return (new java.sql.Date(date.getTime()).toString() + " " + date.getHours() + ":" + date.getMinutes());
	}
	
	/*
	 * Insert a record of a dosage applied into the database.
	 * TODO actually use this.
	 */
	public void medicationApplied(String date, Medication m) {
		try {
			Statement stmt = con.createStatement();
			int i = stmt.executeUpdate("INSERT into dosages (medication_id, date, patient_id) VALUES ( '" + m.getID() + "' , '" + date + "', " + m.getPatientID() + ");");
			System.out.println(i);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Query patient by their name, with wildcards before and after.
	 * Returns a patient object.
	 */
	public Patient queryPatientByName(String name) {
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM patient where patient_name like '%" + name + "%';");
			if (rs.next()) {
				return new Patient(rs);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/*
	 * Query patient by their unique id. No wildcarding. Returns a patient object.
	 */
	public Patient queryPatientById(String id) {
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM patient where id ='" + id + "';");
			if (rs.next()) {
				return new Patient(rs);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/*
	 * Returns the medication identified by id as a medication object.
	 * No wildcarding.
	 */
	public Medication queryMedicationById(String id) {
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM medication where id ='" + id + "';");
			if (rs.next()) {
				return new Medication(rs);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/*
	 * Returns all medications for a given patient as an arraylist.
	 */
	public ArrayList<Medication> queryMedicationByPatient(Patient p) {
		try {
			ArrayList<Medication> meds = new ArrayList<Medication>();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM medication where patient_id = " + p.getId() + ";");
			while(rs.next())  {
				Medication m = new Medication(rs);
				meds.add(m);
			}
			return meds;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/*
	 * Used to set room in the patient database.
	 */
	public void setRoom(String patientID, String room) {
		//test this for real with database
		try {
			Statement stmt = con.createStatement();
			int i = stmt.executeUpdate("UPDATE patient set room_number '" + room + "' where patient_id = '" + patientID + "';");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Delete the specified patient from the database.
	 * Also delete their medications so no further dispensions will happen.
	 * If past is true then also delete all past dosage records.
	 */
	public void deletePatient(String id, boolean past) {
		try {
			Statement stmt = con.createStatement();
			int i = stmt.executeUpdate("DELETE from patient where id = '" + id + "' ;");
			i = stmt.executeUpdate("DELETE from medication where patient_id = '" + id + "' ;");
			if (past) {
				i = stmt.executeUpdate("DELETE from dosages where patient_id = '" + id + "' ;");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/*
	 * Delete a medication record. If past is true then delete all past dosage records.
	 */
	public void deleteMedication(String id, boolean past) {
		try {
			Statement stmt = con.createStatement();
			int i = stmt.executeUpdate("DELETE from medication where id = '" + id + "' ;");
			if (past) {
				i = stmt.executeUpdate("DELETE from dosages where medication_id = '" + id + "' ;");
			}
		} catch (Exception e) {
			e.printStackTrace();
			}
	}
	
	/*
	 * Returns an arraylist of all medications that are to be dispensed in the next minute.
	 */
	public ArrayList<Medication> queryMedicationByTime(String time) {
		try {
			ArrayList<Medication> meds = new ArrayList<Medication>();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM medication where time like '" + time + "%';");
			while(rs.next())  {
				Medication m = new Medication(rs);
				meds.add(m);
				System.out.println(rs.getInt("id")+"  "+rs.getString("medication")+"  "+rs.getInt("dosage") + "  "+rs.getInt("patient_id"));
			}
			return meds;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
