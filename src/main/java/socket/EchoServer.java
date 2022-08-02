package socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        boolean rsl = true;
        try (ServerSocket server = new ServerSocket(9000)) {
            while (rsl) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str;
                    while (!(str = in.readLine()).isEmpty()) {
                        if (str.contains("Hello")) {
                            out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                            out.write("Hello".getBytes());
                        } else if (str.contains("Any")) {
                            out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                            out.write("What".getBytes());
                        } else if (str.contains("Exit")) {
                            socket.close();
                            rsl = false;
                            break;
                        }
                    }
                    out.flush();
                }
            }
        } catch (Exception e) {
            LOG.error("Exception in log error ", e);
        }
    }
}
