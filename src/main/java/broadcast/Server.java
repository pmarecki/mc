package broadcast;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {



    public static void main(String[] args) throws Exception{
        int DEFAULT_PORT = 1234;
        DatagramSocket socket;
        DatagramPacket packet;
        socket = new DatagramSocket(DEFAULT_PORT);
        packet = new DatagramPacket (new byte[1], 1);

        while (true) {
            socket.receive (packet);
            System.out.println("Received from: " + packet.getAddress () + ":" +
                    packet.getPort ());
            byte[] outBuffer = new java.util.Date().toString().getBytes();
            packet.setData (outBuffer);
            packet.setLength (outBuffer.length);
            socket.send(packet);
        }

    }
}
