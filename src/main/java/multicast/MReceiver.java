package multicast;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;

public class MReceiver {

    public static void main(String[] args) throws Exception {
        MulticastSocket socket = null;
        socket = new MulticastSocket(4446);
        socket.setReceiveBufferSize(1<<20);
        InetAddress group = InetAddress.getByName("230.0.0.0");
        socket.joinGroup(group);
        socket.setNetworkInterface(NetworkInterface.getByName("ens3"));

        System.out.println("--------------Receiver--------------");
        System.out.println("SO_RCVBUF (buffer size): " + socket.getReceiveBufferSize());
        System.out.println("socket address: " + socket.getLocalSocketAddress());
        System.out.println("socket iface: " + socket.getInterface());
        System.out.println("socket TTL: " + socket.getTimeToLive());

        byte[] buf = new byte[1024];
        int cnt = 0;
        while(true) {
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);

            String received = new String(packet.getData(), 0, packet.getLength());
//            System.out.println(received);
            if (cnt%1000==0) System.out.println(cnt);
            cnt++;
            if (received.startsWith("ex")) break;
        }
        socket.leaveGroup(group);
        socket.close();
    }
}
