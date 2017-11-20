package com.sysc3010.m7.udp;

import java.io.IOException;
import java.io.*;
import java.net.*;

public class onePi {
	private DatagramPacket sendPacket;
	private DatagramSocket sendSocket;
	public int count=1;

	private int sendPort = 69;

	
	
	public onePi() throws SocketException{
		
		sendSocket=new DatagramSocket();
	}
		
	public void sendto() throws UnknownHostException{
	    byte[] data = new byte[1];
		data[0]=(byte)count;
		
		sendPacket = new DatagramPacket(data, data.length, InetAddress.getLocalHost(), sendPort);
		
	try {
		//	if (received){
				sendSocket.send(sendPacket);

			//}
		} catch (IOException e) {
			e.printStackTrace();
		} 	
	}
	public static void main(String args[]) throws IOException {
		onePi c = new onePi();
		c.sendto();
	}
}