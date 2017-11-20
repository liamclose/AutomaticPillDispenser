package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.sql.Date;
import java.util.regex.Pattern;

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
	
	protected void insertPatient(String[] params) {
		//first verify params
		System.out.println(params[0] + params[1]);
		
		d.insertPatient(params[0], Integer.parseInt(params[1].trim()));
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
		
		if(params.length==4) {
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
		DatagramPacket reply;
		switch(params[0].toLowerCase()) {
		case("insert patient"):
			System.out.println("Add patient");
			if (params.length==3 && validateInsertPatient(params)) {
				d.insertPatient(params[1], Integer.parseInt(params[2].trim()));
				reply = new DatagramPacket("Success".getBytes(), 7, p.getAddress(), p.getPort());
				receiveSocket.send(reply);
			}
			else {
				reply = new DatagramPacket((errorMsg + "2").getBytes(), (errorMsg.length()+1), p.getAddress(), p.getPort());
				receiveSocket.send(reply);
			}
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
		case("get patient"):
			System.out.println("GetPatient");
			Patient pat = d.queryPatientByName(params[1]);
			if (pat==null) {
				System.out.println("null");
				reply = new DatagramPacket((errorMsg + "1").getBytes(), (errorMsg.length()+1), p.getAddress(), p.getPort());
				receiveSocket.send(reply);
			}
			else {
				String s = pat.toString();
				reply = new DatagramPacket(s.getBytes(), s.length(), p.getAddress(), p.getPort());
				receiveSocket.send(reply);				
			}
			return;
		case("insert medication"):
			System.out.println("New Medication");
			if (validateInsertMedication(params)) {
				d.insertMedication(Integer.parseInt(params[1].trim()), params[2], Integer.parseInt(params[3].trim()));
				reply = new DatagramPacket("Success".getBytes(), 7, p.getAddress(), p.getPort());
				receiveSocket.send(reply);
			} else {
				reply = new DatagramPacket((errorMsg + "2").getBytes(), (errorMsg.length()+1), p.getAddress(), p.getPort());
				receiveSocket.send(reply);
			}
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
			} catch (SocketTimeoutException e) {
				System.out.println("timed out");
			}
			catch (IOException e) {
				System.out.print("IO Exception: likely:");
				e.printStackTrace();
				System.exit(1);
			}
			//receivedMessage = new String(data, 0, receivePacket.getLength());
			try {
				dispatch(receivePacket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Received");
		//	System.out.println(receivedMessage);
		}
	}
	public static void main(String[] args) {
		ServerMain c = new ServerMain();
		c.receive();
		c.receiveSocket.close(); //close the receiving socket
	}
}
