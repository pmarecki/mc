package tcp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

class TcpReceiver {
    public static void main(String argv[]) throws Exception {
        String req;
        String repl;
        ServerSocket acceptSocket = new ServerSocket(4446);
        int cnt = 0;
        while (true) {
            Socket socket = acceptSocket.accept();
//            System.out.println("Accepted on(to) port:" + socket.getPort());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            req = in.readLine();
            System.out.println("Received: " + req);
            if (++cnt%1000==0) System.out.println(cnt);
            repl = req.toUpperCase() + '\n';
            out.writeBytes(repl);
            out.flush();
        }
    }
}
