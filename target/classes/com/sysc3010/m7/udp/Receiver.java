import java.io.IOException;
import java.io.*;
import java.net.*;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class Receiver {
    private DatagramPacket receivePacket;
    private DatagramSocket receiveSocket;
    private int sendPort = 69; //set the default port to connect sender and receiver
    GpioPinDigitalOutput speaker;
    final GpioController gpio = GpioFactory.getInstance();
    
    public Receiver() throws SocketException {
        receiveSocket = new DatagramSocket(sendPort); //receive the socket fron sender by the same port
    }
    
    public void receive() throws InterruptedException{     
        byte[] data = new byte[512];  //creat a new byte arrry with size of 512 to store the received packet
        receivePacket = new DatagramPacket(data, data.length); //put the packet to the empty array list
        speaker = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00);
        while(true){
        try {
            receiveSocket.receive(receivePacket);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        byte[] data1;
        data1 = receivePacket.getData(); 
        int i = Integer.parseInt(new String(data1, 0, receivePacket.getLength()));        
        if(data1.length >0) {
            rotateMotor(i);
        }
       }
    }  
    
    // Method to rotate the motor the inputted amount of times and also sounds buzzer after turning completley 
    public void rotateMotor(int turns) throws InterruptedException{ 
        final GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03);
        pin.low();
        pin.setShutdownOptions(true, PinState.LOW);
        for (int x = 0;x<turns;x++) {    
           pin.high(); 
           Thread.sleep(1350);
           pin.low(); 
        }         
        speaker.high();
        Thread.sleep(1350);
        speaker.low();
        gpio.shutdown();
    }    
    
    public static void main(String args[]) throws IOException, InterruptedException {    
        Receiver test = new Receiver();
        
        test.receive();
    }
}
    


