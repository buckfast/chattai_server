
package chattai;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Chatserver {

    ServerSocket ss;
    Socket s;

    public Chatserver() throws IOException {

    }

    public void serve() throws IOException {

        this.ss = new ServerSocket(0, 3);
        System.out.println(ss.getLocalPort());

        while (true) {
            this.s = ss.accept();
            CommandInterpreter ci = new CommandInterpreter(s.getInputStream(), new PrintStream(s.getOutputStream(), true));
            Thread t = new Thread(ci);
            t.start();
        }

    }

}
