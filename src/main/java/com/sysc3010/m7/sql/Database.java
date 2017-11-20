package com.sysc3010.m7.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.mysql.jdbc.PreparedStatement;

@Service
public class Database {

    public static final String DATABASE = "jdbc:mysql://localhost:3306/dispenser";
    public static final String USER = "root";
    public static final String PASS = "tester";

    // TODO: Complete at a later date
    public Patient queryPatientsByName(String name) {
        return null;
    }

    // TODO: Complete at a later date
    public Patient queryPatientsById(String id) {
        return null;
    }

    // TODO: Complete at a later date
    public boolean insertPatient(Patient patient) {
        return false;
    }

    // TODO: Complete at a later date
    public Connection getConnection() {
        return null;
    }

    public static void insertPatient(Connection con, String name, int room) {
        try {
            Statement stmt = con.createStatement();
            int i = stmt.executeUpdate(
                    "INSERT into patient (patient_name, room_number) VALUES ( '" + name + "' , " + room + ");");
            System.out.println(i);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void insertMedication(Connection con, int patient_id, String medication, int dosage) {
        try {
            Statement stmt = con.createStatement();
            int i = stmt.executeUpdate("INSERT into medication (medication, dosage, patient_id) VALUES ( '" + medication
                    + "' , " + dosage + ", " + patient_id + ");");
            System.out.println(i);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static String toSQLDateTime(java.util.Date date) {
        System.out.println(date.toString());
        return (new java.sql.Date(date.getTime()).toString() + " " + date.getHours() + ":" + date.getMinutes());

    }

    public static void medicationApplied(Connection con, java.util.Date date, Medication m) {
        try {
            Statement stmt = con.createStatement();
            String s = toSQLDateTime(date);
            System.out.println(s);
            int i = stmt.executeUpdate("INSERT into dosages (medication_id, date, patient_id) VALUES ( '" + m.id
                    + "' , '" + s + "', " + m.patient_id + ");");
            System.out.println(i);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static Patient queryPatientByName(Connection con, String name) {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM patient where patient_name like '%" + name + "%';");
            if (rs.next()) {
                return new Patient(rs);
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static ArrayList<Medication> queryMedicationByPatient(Connection con, Patient p) {
        try {
            ArrayList<Medication> meds = new ArrayList<Medication>();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM medication where patient_id = " + p.getId() + ";");
            while (rs.next()) {
                Medication m = new Medication(rs);
                meds.add(m);
                System.out.println(rs.getInt("id") + "  " + rs.getString("medication") + "  " + rs.getInt("dosage")
                        + "  " + rs.getInt("patient_id"));
            }
            return meds;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void dispense(Connection con) {
        try {

            // this is temporary code, actual code will involve scheduling and communication
            Statement stmt = con.createStatement();
            java.util.Date d = new java.util.Date();
            String s = d.getHours() + ":" + d.getMinutes();
            ResultSet rs = stmt.executeQuery("SELECT * FROM medication WHERE time LIKE '" + s + "%';");
            while (rs.next())
                System.out.println(rs.getInt("id") + "  " + rs.getString("medication") + "  " + rs.getInt("dosage")
                        + "  " + rs.getInt("patient_id"));
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public static void main(String args[]) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(DATABASE, USER, PASS);
            Patient p = queryPatientByName(con, "test");
            System.out.println(p.toString());
            ArrayList<Medication> m = queryMedicationByPatient(con, p);
            System.out.println(m);
            medicationApplied(con, new java.util.Date(), m.get(0));
            // while (true) {
            System.out.println("loop");
            dispense(con);
            // Thread.sleep(60000);
            // }
            // insertPatient(con, "Other", 3);
            // Statement stmt=con.createStatement();
            // ResultSet rs=stmt.executeQuery("select * from patient");
            // while(rs.next())
            // System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
            // insertMedication(con, 1,"ibuprofen", 200);
            // rs=stmt.executeQuery("select * from medication");
            // while(rs.next())
            // System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}