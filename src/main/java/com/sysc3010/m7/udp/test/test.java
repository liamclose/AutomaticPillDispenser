package com.sysc3010.m7.udp.test;


import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.junit.Test;
import org.mockito.Mock;

public class test {

	@Mock
	private DatagramPacket receivePacket;
	@Mock
	private DatagramSocket receiveSocket;

	private int sendPort = 69;

	@Test
	public void testSecond() throws SocketException, UnknownHostException {
		//receivePacket = mock(DatagramPacket.class);
		
		
		//onePi c = new onePi();
		//c.sendto();
		

		byte[] data = new byte[512];
		
		receiveSocket.setSoTimeout(5000);

		try {
			receiveSocket.receive(receivePacket);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		when(receivePacket.getData()).thenReturn(null);

		byte[] data1;
		data1 = receivePacket.getData();
		System.out.println(data1.toString());

		assertTrue(receivePacket.getLength() == 0);

	}

}
