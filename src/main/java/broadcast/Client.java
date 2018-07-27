package broadcast;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import static java.net.InetAddress.getByName;

public class Client {

    public static void main(String[] args) throws Exception {
        String hostname= "10.10.0.55";
        int port=1234;
        InetAddress host;
        DatagramSocket socket;
        DatagramPacket packet;

        host = getByName(hostname);
        System.out.println(host);
        socket = new DatagramSocket(null);
        packet = new DatagramPacket(new byte[100], 0, host, port);
        socket.send (packet);
        packet.setLength(100);
        socket.receive (packet);
        socket.close ();

        String response = new String(packet.getData());
        System.out.println(response);
    }

}
