import java.io.IOException;
import java.io.*;
import java.net.*;

public class Sender {
	private DatagramPacket sendPacket;
	private DatagramSocket sendSocket;
	public int count=1;

	private int sendPort = 69;

	
	
	public Sender() throws SocketException{
		
		sendSocket=new DatagramSocket();
	}
		
	public void sendto() throws UnknownHostException{
	    byte[] data = "3".getBytes();
		InetAddress address=InetAddress.getByName("10.0.0.2");
		
		sendPacket = new DatagramPacket(data, data.length, address, sendPort);
		
	try {
	
				sendSocket.send(sendPacket);

			
		} catch (IOException e) {
			e.printStackTrace();
		} 	
	}
	public static void main(String args[]) throws IOException {
		Sender test = new Sender();
		test.sendto();
	}
}