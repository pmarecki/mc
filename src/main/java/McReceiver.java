import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import static java.lang.Thread.sleep;
import static java.net.NetworkInterface.getByName;

public class McReceiver {
    static MulticastSocket socket = null;
    static byte[] buf = new byte[256];

    public static void main(String[] args) throws Exception {
        socket = new MulticastSocket(4446);
        InetAddress group = InetAddress.getByName("230.0.0.0");
        socket.setNetworkInterface(getByName("ens3"));
        socket.joinGroup(group);
        sleep(1000);
        while (true) {
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);
            String received = new String(packet.getData(), 0, packet.getLength());
            if ("end".equals(received)) {
                break;
            }
        }
        socket.leaveGroup(group);
        socket.close();
    }
}