package com.sysc3010.m7.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
public class second {

	private DatagramPacket receivePacket;
	private DatagramSocket receiveSocket;

	private int sendPort = 69;
	
	public second() throws SocketException {
		receiveSocket = new DatagramSocket(sendPort);
	}
	public void receive() {
	 
		byte[] data = new byte[512];
		receivePacket = new DatagramPacket(data, data.length);
		
		try {
			receiveSocket.receive(receivePacket);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		byte[] data1;
		data1 = receivePacket.getData();
		System.out.println(data1.toString());
	}
	public static void main(String args[]) throws IOException {
		second c = new second();
		c.receive();
	}
	}
	


