package server;

import java.io.IOException;
import java.io.StringWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mysql.jdbc.util.ErrorMappingsDocGenerator;
import com.sysc3010.m7.sql.Database;

public class ServerMain extends Thread {

	DatagramSocket sendSocket, receiveSocket;
	//String receivedMessage;
	static String errorMsg = "Something went wrong. Error code:";
	
	//Error codes
	//0 - unhandled, invalid fn etc
	//1 - Invalid search (or doesn't exist, could be fine)
	//2 - Invalid insert/alter
	//3 - Nothing was deleted,  not really an error
	Database d;
	
	public ServerMain() {
		try {
			receiveSocket = new DatagramSocket(8700);
			d = new Database();
		} catch (SocketException se) {
			se.printStackTrace();
			System.exit(1);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void run() {
		while (true) {
			this.receive();
		}
	}
	
	
	public static boolean validateInsertPatient(String[] params) {
		
		if(params.length==3) {
			try {
				Integer.parseInt(params[2].trim());
				return true;
			} catch (Exception e) {
				
			}
		}
		return false;
	}
	
	public static boolean validateSetRoom(String[] params) {
		
		if(params.length==3) {
			try {
				Integer.parseInt(params[1].trim());
				Integer.parseInt(params[2].trim());
				return true;
			} catch (Exception e) {
				
			}
		}
		return false;
	}
	
	public boolean validateInsertMedication(String[] params) {
		
		if(params.length==5) {
			try {
				Integer.parseInt(params[1].trim());
				Integer.parseInt(params[3].trim());
				return !(null==(d.queryPatientById(params[1])));
			} catch (Exception e) {
				
			}
		}
		return false;
	}
	
	public boolean validateApplyDosage(String[] params) {
		if(params.length==3) {
			try {
				params[1].trim(); //check fr date
				Integer.parseInt(params[2].trim());
				
				return !(null==(d.queryMedicationById(params[2])));
			} catch (Exception e) {
				
			}
		}
		return false;
	}
	
	public boolean validateRemove(String[] params) {
		if(params.length==3) {
			try {
				Integer.parseInt(params[1].trim());
				return (("true".equals(params[2].trim()))||"false".equals(params[2].trim()));
			} catch (Exception e) {
				
			}
		}
		return false;
	}
	
	public boolean validateMed(String[] params) {
		if(params.length==2) {
			try {
				Integer.parseInt(params[1].trim());
				return true;
			} catch (Exception e) {
				
			}
		}
		return false;
	}
	
	public void dispatch(DatagramPacket p) throws IOException{
		String receivedPacket = new String(p.getData(), 0, p.getLength());
		String[] params = receivedPacket.split(", ");
		Gson gson;
		Patient pat;
		StringWriter s;
		DatagramPacket reply;
		System.out.println("Got: " + params);
		System.out.println(params.toString());
		switch(params[0].toLowerCase()) {
		case("write patient"):
			System.out.println("Add patient");
			gson = new GsonBuilder().create();
			s = new StringWriter();
			pat = gson.fromJson(params[1], Patient.class);
			//if (params.length==3 && validateInsertPatient(params)) {
				d.insertPatient(pat);
				reply = new DatagramPacket("success".getBytes(), 7, p.getAddress(), p.getPort());
				receiveSocket.send(reply);
		//	}
		//	else {
		//		reply = new DatagramPacket((errorMsg + "2").getBytes(), (errorMsg.length()+1), p.getAddress(), p.getPort());
			//	receiveSocket.send(reply);
	//		}
			return;		
		case("set room"):
			System.out.println("Set room");
			if (params.length==3 && validateSetRoom(params)) {
				d.setRoom(params[1], params[2]);
				reply = new DatagramPacket("Success".getBytes(), 7, p.getAddress(), p.getPort());
				receiveSocket.send(reply);
			}
			else {
				reply = new DatagramPacket((errorMsg + "2").getBytes(), (errorMsg.length()+1), p.getAddress(), p.getPort());
				receiveSocket.send(reply);
			}
			return;
		case("get id"):
			System.out.println("GetPatient");
			pat = d.queryPatientById(params[1]);
			if (pat==null) {
				
				reply = new DatagramPacket((errorMsg + "1").getBytes(), (errorMsg.length()+1), p.getAddress(), p.getPort());
				receiveSocket.send(reply);
			}
			else {
				gson = new GsonBuilder().create();
				s = new StringWriter();
		        gson.toJson(pat, s);
		        String a = s.toString();
				reply = new DatagramPacket(a.getBytes(), a.length(), p.getAddress(), p.getPort());
				receiveSocket.send(reply);				
			}
			return;
		case("all"):
			System.out.println("GetPatients");
			ArrayList<Patient> pats = d.getPatients();
		/*	if (pasts==null) {
				System.out.println("null");
				reply = new DatagramPacket((errorMsg + "1").getBytes(), (errorMsg.length()+1), p.getAddress(), p.getPort());
				receiveSocket.send(reply);
			}*/
			//else {
				gson = new GsonBuilder().create();
				s = new StringWriter();
		        gson.toJson(pats, s);
		        String a = s.toString();
				reply = new DatagramPacket(a.getBytes(), a.length(), p.getAddress(), p.getPort());
				receiveSocket.send(reply);				
		//	}
			return;
		case("write med"):
			System.out.println("New Medication");
			gson = new GsonBuilder().create();
			s = new StringWriter();
			System.out.println(params[1]);
			System.out.println(params);
			Medication med = gson.fromJson(params[1], Medication.class);
		//if (params.length==3 && validateInsertPatient(params)) {
			
			d.insertMedication(med);
		
			return;
		case("dosage applied"):
			System.out.println("Add dosage");
			if (validateApplyDosage(params)) {
				d.medicationApplied(params[1], d.queryMedicationById(params[2].trim()));
				reply = new DatagramPacket("Success".getBytes(), 7, p.getAddress(), p.getPort());
				receiveSocket.send(reply);
			} else {
				reply = new DatagramPacket((errorMsg + "2").getBytes(), (errorMsg.length()+1), p.getAddress(), p.getPort());
				receiveSocket.send(reply);
			}
			return;
			
		case("get ready"):
			ArrayList<Medication> meds = d.queryMedicationByTime(params[1]);
			System.out.println(params[1]);
			gson = new GsonBuilder().create();
			s = new StringWriter();
			gson.toJson(meds, s);
			a = s.toString();
			reply = new DatagramPacket(a.getBytes(), a.length(), p.getAddress(), p.getPort());
			receiveSocket.send(reply);	
			return;
		case("remove patient"):
			System.out.println("Remove Patient");
			if (validateRemove(params)) {
				d.deletePatient(params[1], Boolean.parseBoolean(params[2].trim()));
				reply = new DatagramPacket("Success".getBytes(), 7, p.getAddress(), p.getPort());
				receiveSocket.send(reply);
			}
			else {
				reply = new DatagramPacket((errorMsg + "3").getBytes(), (errorMsg.length()+1), p.getAddress(), p.getPort());
				receiveSocket.send(reply);
			}
			return;
		case("remove medication"):
			System.out.println("Remove Dosage");
			if (validateRemove(params)) {
				System.out.println();
				d.deleteMedication(params[1], Boolean.parseBoolean(params[2].trim()));
				reply = new DatagramPacket("Success".getBytes(), 7, p.getAddress(), p.getPort());
				receiveSocket.send(reply);
			} else {
				reply = new DatagramPacket((errorMsg + "3").getBytes(), (errorMsg.length()+1), p.getAddress(), p.getPort());
				receiveSocket.send(reply);
			}
			return;
		case("get dosage"):
			System.out.println("Get Dosage Info");
			if (validateMed(params)) {
				Medication m = d.queryMedicationById(params[1]);
				if (m==null) {
					System.out.println("why");
					reply = new DatagramPacket((errorMsg + "1").getBytes(), (errorMsg.length()+1), p.getAddress(), p.getPort());

				} else {
					System.out.println("not null");
					reply = new DatagramPacket(m.toString().getBytes(), m.toString().length(), p.getAddress(), p.getPort());
				}
				receiveSocket.send(reply);
			} else {
				reply = new DatagramPacket((errorMsg + "1").getBytes(), (errorMsg.length()+1), p.getAddress(), p.getPort());
				receiveSocket.send(reply);
			}
			return;
		}
		reply = new DatagramPacket((errorMsg + "0").getBytes(), (errorMsg.length()+1), p.getAddress(), p.getPort());
		receiveSocket.send(reply);
	}
	
	public void receive() {
		byte data[] = new byte[516];
		DatagramPacket receivePacket = new DatagramPacket(data, data.length);
		while(true) {
			try {
				receiveSocket.setSoTimeout(600);
				receiveSocket.receive(receivePacket);
				System.out.println("dispatching");
				dispatch(receivePacket);

			} catch (SocketTimeoutException e) {
				
			}
			catch (IOException e) {
				System.out.print("IO Exception: likely:");
				e.printStackTrace();
				System.exit(1);
			}
			
		//	System.out.println(receivedMessage);
		}
	}
	public static void main(String[] args) {
		ServerMain m = new ServerMain();
		System.out.println(m.d.getPatients().toString());
		/*ArrayList<Medication> ed = m.d.queryMedicationByTime("13:57");
		Gson gson = new GsonBuilder().create();
		StringWriter s = new StringWriter();
        gson.toJson(ed, s);
        String a = s.toString();
        System.out.println(a);
        ArrayList<Medication> p = gson.fromJson(a, ArrayList.class);
        ArrayList<Patient> j = m.d.getPatients();
        System.out.println(Database.USER + Database.PASS); */
      //  m.d.insertMedication(1, "test2", 3, "14:08:00");
        m.receive();
	
	}
}
