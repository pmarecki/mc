package tcp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class TcpSender {
    public static void main(String[] args) throws Exception {
        String sentence;
        String repl;

        for (int i = 0; i < 10000; i++) {
            Socket socket = new Socket("localhost", 6789);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String msg = "AbraKadabra\n";

            out.writeBytes(msg);
            out.flush();
            repl = in.readLine();
//            System.out.println("reply: " + repl);
            socket.close();
        }

    }
}
