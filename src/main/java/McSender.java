import java.io.IOException;
import java.net.*;

import static java.net.NetworkInterface.getByName;

public class McSender {
    static MulticastSocket socket;
    static InetAddress group;
    static byte[] buf;

    public static void main(String[] args) throws Exception {
        String message = "AbraKadabra";
        socket = new MulticastSocket();
        group = InetAddress.getByName("230.0.0.0");
        buf = message.getBytes();
        socket.setNetworkInterface(getByName("ens3"));
        for (int i = 0; i < 10; i++) {
            DatagramPacket packet = new DatagramPacket(buf, buf.length, group, 4446);

            socket.send(packet);
        }
        socket.close();

    }
}