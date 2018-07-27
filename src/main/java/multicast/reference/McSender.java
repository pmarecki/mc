package multicast.reference;

import java.io.IOException;
import java.net.*;

import static java.net.NetworkInterface.getByName;

public class McSender {

    public static void main(String[] args) throws Exception {
        MulticastSocket socket;
        InetAddress group;
        socket = new MulticastSocket();
        group = InetAddress.getByName("230.0.0.0");
        String message = "AbraKadabra";

        byte[] buf = message.getBytes();
//        socket.setNetworkInterface(getByName("ens3"));
        for (int i = 0; i < 10; i++) {
            DatagramPacket packet = new DatagramPacket(buf, buf.length, group, 4446);
            socket.send(packet);
        }
        socket.close();

    }
}