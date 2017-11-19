package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

public class ServerMain extends Thread {

	DatagramSocket sendSocket, receiveSocket;
	
	public ServerMain() {
		try {
			receiveSocket = new DatagramSocket(8700);
		} catch (SocketException se) {
			se.printStackTrace();
			System.exit(1);
		}

	}
	
	public void receive() {
		byte data[] = new byte[516];
		DatagramPacket receivePacket = new DatagramPacket(data, data.length);
		while(true) {
			try {
				receiveSocket.setSoTimeout(60000); //timeout for quit purposes
				receiveSocket.receive(receivePacket);
			} catch (SocketTimeoutException e) {
				System.out.println("timed out");
				return;
			}
			catch (IOException e) {
				System.out.print("IO Exception: likely:");
				e.printStackTrace();
				System.exit(1);
			}
			System.out.println("Got something somehow");
		}
	}
	public static void main(String[] args) {
		ServerMain c = new ServerMain();
		c.receive();
		c.receiveSocket.close(); //close the receiving socket
	}
}
