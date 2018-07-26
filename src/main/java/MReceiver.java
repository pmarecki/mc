import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import static java.net.NetworkInterface.getByName;

public class MReceiver {
    static String address = "239.255.11.11";
    static String iface = "ens3";
    static Integer port = 11111;

    public static void main(String[] args) throws Exception {
        if (args.length<2) args = new String[]{"1000", "100000"};    //packet_size, buffer_size
        int packetSize = Integer.valueOf(args[0]);
        int bufferSize = Integer.valueOf(args[1]);

        MulticastSocket socket = new MulticastSocket(port);
        socket.setReceiveBufferSize(bufferSize); //10MB

        System.out.println("--------------Receiver--------------");
        System.out.println("SO_RCVBUF (buffer size): " + socket.getReceiveBufferSize());
        System.out.println("Packet size(B):" + packetSize);

        InetAddress group = InetAddress.getByName(address);
        socket.setNetworkInterface(getByName(iface));
        socket.joinGroup(group);
        System.out.println("multicast receiver on " + address + " listens...");

        byte[] buf = new byte[packetSize];
        int num = 0;
        while(true) {
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);
            String line = new String(packet.getData());
            System.out.println(line);
            num++;
            if (num%100==1) System.out.print("*");
            if (line.startsWith("ex")) break;
        }
        num--;
        System.out.println("\n" + num + " packets received");
        socket.leaveGroup(group);
        socket.close();
    }
}
