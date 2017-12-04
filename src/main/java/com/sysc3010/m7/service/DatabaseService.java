package com.sysc3010.m7.service;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mysql.fabric.xmlrpc.base.Data;

import server.Medication;
import server.Patient;

@Service
public class DatabaseService {

     private String databaseAddress = "10.0.0.71";
   // private String databaseAddress = "172.17.58.166";
    private int port = 8700;

    private Gson gson;

    //private DatagramSocket inSocket;

    @PostConstruct
    public void setup() {
        //inSocket = new DatagramSocket();
        //inSocket.setSoTimeout(1000);
        gson = new GsonBuilder().create();
    }

    private DatagramSocket getSocket() {
        try {
            DatagramSocket sock = new DatagramSocket();
            sock.setSoTimeout(1000);
            return sock;
                    
        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean writePatientData(Patient newPatient, Medication newMed) {
        String patientJson = gson.toJson(newPatient);
        String patientPacketString = "write patient, " + patientJson;
        DatagramSocket socket = getSocket();
        sendPacket(patientPacketString, socket);
        
        byte[] patientData = new byte[512];
        DatagramPacket responsePacket = new DatagramPacket(patientData, 512);
        try {
            socket.receive(responsePacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        String medJson = gson.toJson(newMed);
        sendPacket("write med, "+ medJson, socket);

      return true;

    }

    public void sendPacket(String packetString, DatagramSocket socket) {
        DatagramPacket newPatientPacket = new DatagramPacket(packetString.getBytes(), packetString.length());
        newPatientPacket.setSocketAddress(new InetSocketAddress(databaseAddress, port));
        try {
            socket.send(newPatientPacket);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public String receivePacket(DatagramSocket socket) throws SocketTimeoutException {
        byte[] patientData = new byte[512];
        DatagramPacket responsePacket = new DatagramPacket(patientData, 512);

        try {
            socket.receive(responsePacket);
        } catch (IOException e) {
            if (e instanceof SocketTimeoutException) {
                throw new SocketTimeoutException(e.getMessage());
            } else {
                e.printStackTrace();
            }
        }
        return new String(responsePacket.getData(), 0, responsePacket.getLength());
    }

    public ArrayList<Medication> getMedsToBeDispensed() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int intMin = cal.get(Calendar.MINUTE);
        String minutes = (intMin < 10) ? ("0" + intMin) : ("" + intMin);
        String packetString = "get ready, " + hour + ":" + minutes;
        DatagramSocket socket = getSocket();
        sendPacket(packetString, socket);

        String jsonResponse = null;
        try {
            jsonResponse = receivePacket(socket);
        } catch (SocketTimeoutException e) {
            return new ArrayList<Medication>();
        }

        TypeToken<ArrayList<Medication>> tt = new TypeToken<ArrayList<Medication>>() {
        };
        Type type = tt.getType();
        ArrayList<Medication> medications = gson.fromJson(jsonResponse, type);

        return medications;

    }

    public Patient getPatientById(String id) {
        try {
            String getById = "get id, " + id;
            DatagramPacket idPacket = new DatagramPacket(getById.getBytes(), getById.getBytes().length);
            idPacket.setSocketAddress(new InetSocketAddress(databaseAddress, port));
            DatagramSocket socket = getSocket();
            socket.send(idPacket);

            byte[] patientData = new byte[512];
            DatagramPacket patientPacket = new DatagramPacket(patientData, 512);

            socket.receive(patientPacket);

            String patientJson = new String(patientPacket.getData(), 0, patientPacket.getLength());
            System.out.println(patientJson);
            Patient patient = gson.fromJson(patientJson, Patient.class);
            return patient;
        } catch (IOException e) {
            // TODO Try probably shouldn't wrap everything
            e.printStackTrace();
        }
        return null;
    }

    public List<Patient> getAllPatients() {
        
        DatagramSocket socket = getSocket();
        sendPacket("all", socket);
        
        String jsonResponse = null;
        try {
            jsonResponse = receivePacket(socket);
            System.out.println(jsonResponse);
        } catch (SocketTimeoutException e) {
            return new ArrayList<Patient>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        TypeToken<ArrayList<Patient>> tt = new TypeToken<ArrayList<Patient>>() {
        };
        Type type = tt.getType();
        ArrayList<Patient> patients = gson.fromJson(jsonResponse, type);

        return patients;
    }
}
