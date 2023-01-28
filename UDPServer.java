// 62050238 Siriya Saenkhom-or

import java.net.*;
import java.text.SimpleDateFormat;  
import java.util.Date;  

class UDPServer {
    public static void main(String args[]) throws Exception {

        DatagramSocket serverSocket = new DatagramSocket(9876);
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];
        while (true) {
            System.out.println("Waiting for client connection at port number 9876");
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            // extract client location
            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();
            
            // get datetime now
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
            Date date = new Date();
            String datetime = formatter.format(date);
            sendData = datetime.getBytes();

            // send back to client
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
            serverSocket.send(sendPacket);
        }
    }
}