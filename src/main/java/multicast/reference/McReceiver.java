package multicast.reference;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;

import static java.lang.Thread.sleep;
import static java.net.NetworkInterface.getByName;

public class McReceiver {

    public static void main(String[] args) throws Exception {
        MulticastSocket socket = null;
        byte[] buf = new byte[1024];
        socket = new MulticastSocket(4446);
        InetAddress group = InetAddress.getByName("230.0.0.0");
        socket.joinGroup(group);

        System.out.println("Multicast receiver running at:" + socket.getLocalSocketAddress());
        while (true) {
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);
            String received = new String(packet.getData(), 0, packet.getLength());
            System.out.println(received);
            if ("end".equals(received)) {
                break;
            }
        }
        socket.leaveGroup(group);
        socket.close();
    }
}