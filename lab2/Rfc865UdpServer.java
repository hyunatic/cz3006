package lab2;

import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class Rfc865UdpServer {
    public static void main(String[] argv) {
        //
        // 1. Open UDP socket at well-known port
        //
        DatagramSocket socket;
        String Msg = "Hello Client, Im from 10.27.228.244";
        byte[] SendingBytes = Msg.getBytes();

        // QOTD Port number
        int port = 17;
        try {
            socket = new DatagramSocket(port);
        } catch (SocketException e) {
            System.out.println("Socket Error");
            socket = null;
        }

        byte[] receivingByte = new byte[2048];
        while (true) {
            try {
                //
                // 2. Listen for UDP request from client
                //
                DatagramPacket request = new DatagramPacket(receivingByte, receivingByte.length);
                socket.receive(request);
                //
                // 3. Send UDP reply to client
                //
                DatagramPacket reply = new DatagramPacket(SendingBytes, SendingBytes.length, request.getAddress(),
                        request.getPort());

                // Decoding the byte into UTF8
                String clientMsg = new String(request.getData(), StandardCharsets.UTF_8);
                if(new String(
						 request.getData(), 0, request.getLength()).equals("Hello Server")) {
					 System.out.println("Message received");
					 socket.send(reply);
				 }
            } catch (IOException e) {
            }
        }
    }
}