package lab2;

import java.net.*;
import java.io.*;

public class Rfc865UdpClient {
    public static void main(String[] argv) {
        //
        // 1. Open UDP socket
        //
        DatagramSocket socket;
        String Msg = "Hello Server";
        byte[] SendingBytes = Msg.getBytes();
        byte[] buffer = new byte[65535];

        // QOTD port number
        int port = 17;

        // Neighbour IP address
        InetAddress IP;
        try{
            IP = InetAddress.getByName("10.27.228.244");
        }
        catch (UnknownHostException e){
            System.out.println("Cannot get IP address");
            IP = null;
        }
        try {
            socket = new DatagramSocket(port);
        } catch (SocketException e) {
            System.out.println(e);
            socket = null;
        }

        try {
            //
            // 2. Send UDP request to server
            //
            DatagramPacket request = new DatagramPacket(SendingBytes, SendingBytes.length, IP, port);
            socket.send(request);
            //
            // 3. Receive UDP reply from server
            //
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            socket.receive(reply);
        } catch (IOException e) {
        }
    }

}
