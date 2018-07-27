package multicast;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import static java.lang.System.currentTimeMillis;
import static java.lang.System.nanoTime;

public class MSender {
    private static byte[] createMessage(int bytes) {
        int n = bytes/10;
        StringBuilder sb = new StringBuilder();
        while(n-->0) sb.append("1234567890");
        return sb.toString().getBytes();
    }

    public static void waitMicroseconds(long delay) {
        delay *= 1000;
        long ss = nanoTime();
        while(ss + delay >= nanoTime());
    }

    public static void main(String[] args) throws Exception {
        int packetSize = 1000;
        int packetCount = 4;
        int delayUsec = 1;
        int port = 4446;

        MulticastSocket socket = new MulticastSocket();
        InetAddress net = InetAddress.getByName("230.0.0.0");

        byte[] data = createMessage(packetSize);
//        socket.setSendBufferSize(500000);
//        socket.setTimeToLive(10);

        System.out.println("--------------Sender--------------");
        System.out.println("SO_SNDBUF (buffer size):" + socket.getSendBufferSize());
        System.out.println("Packet size:" + packetSize);
        System.out.println("Packet count:" + packetCount);

        long st = currentTimeMillis();
        System.out.println("<<< writer starts >>>");

        DatagramPacket packet = new DatagramPacket(data, data.length, net, port);
        for (int i = 0; i < packetCount; i++) {
            socket.send(packet);
            waitMicroseconds(delayUsec);
        }
        socket.send(new DatagramPacket("exit".getBytes(), 4, net, port));
        System.out.println("<<< writer finished (" + (currentTimeMillis()-st) + "ms) >>>");
        socket.close();
    }
}