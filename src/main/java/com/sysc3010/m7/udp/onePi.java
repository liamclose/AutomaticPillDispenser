package com.sysc3010.m7.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.springframework.stereotype.Service;

@Service
public class onePi {
    private DatagramPacket sendPacket;
    private DatagramSocket sendSocket;
    public int count = 1;

    private int sendPort = 69;

    public onePi() throws SocketException {

        sendSocket = new DatagramSocket();
    }

    public boolean sendto(String ip) throws UnknownHostException {
        byte[] data = new byte[1];
        data[0] = (byte) count;

        sendPacket = new DatagramPacket(data, data.length, InetAddress.getByName(ip), sendPort);

        try {
            // if (received){
            sendSocket.send(sendPacket);

            // }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String args[]) throws IOException {
        onePi c = new onePi();
        //c.sendto();
    }
}