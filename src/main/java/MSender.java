import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MSender {
    static String address = "239.255.11.11";
    static String iface = "ens3";
    static Integer port = 11112;

    private static byte[] createMessage(int bytes) {
        int n = bytes/10;
        StringBuilder sb = new StringBuilder();
        while(n-->0) sb.append("1234567890");
        return sb.toString().getBytes();
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Multicast sender, usage:");
        System.out.println("MReceiver pktsize pktcount delayusec");
        if (args.length<3) args = new String[]{"1000", "1000", "10"}; //packet_size, packet_count, delay_usec

        int packetSize = Integer.valueOf(args[0]);
        int packetCnt = Integer.valueOf(args[1]);
        int delayUsec = Integer.valueOf(args[2]);

        MulticastSocket socket = new MulticastSocket();
        InetAddress net = InetAddress.getByName(address);

        byte[] data = createMessage(packetSize);
//        socket.setSendBufferSize(500000);
//        socket.setTimeToLive(10);

        System.out.println("--------------Sender--------------");
        System.out.println("SO_SNDBUF (buffer size):" + socket.getSendBufferSize());
        System.out.println("Packet size:" + packetSize);
        System.out.println("Packet count:" + packetCnt);
        System.out.println("<<< writer starts >>>");

        DatagramPacket packet = new DatagramPacket(data, data.length, net, port);
        Thread.sleep(1);
        for (int i = 0; i < packetCnt; i++) {
            socket.send(packet);
            if (delayUsec>0) NanoSleep.waitMicroseconds(delayUsec);
        }
        System.out.println();
        socket.send(new DatagramPacket("exit".getBytes(), 4, net, port));
        System.out.println("<<< writer finished >>>");
        socket.close();
    }
}