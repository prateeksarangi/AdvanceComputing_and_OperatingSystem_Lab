import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ClientThread implements Runnable {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private String chatName;
    private String password;

    private Thread thread;

    private volatile boolean isRunning = true;

    private int opcode;
    private static Map<String, String> userpass = new HashMap<String, String>();

    static {
        userpass.put("Person1", "pass");
        userpass.put("Person2", "word");
    }

    public ClientThread(Socket socket) {
        try {
            this.socket = socket;

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            thread = new Thread(this);
            thread.start();

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void run() {
        try {
            while (isRunning) {
                if (!in.ready())
                    continue;

                opcode = Integer.parseInt(in.readLine());
                switch (opcode) {
                    case Opcode.CLIENT_USERNAME:
                        chatName = in.readLine();

                        System.out.println(chatName + " is reqesting to connect.");
                        boolean result1 = userpass.containsKey(chatName);
                        if (result1) {
                            System.out.println(chatName + " is a valid username.");
                            out.println(Opcode.CLIENT_PASSWORD);
                        } else {
                            System.out.println(chatName + " is a invalid username.");
                            out.println(Opcode.CLIENT_INVALID_USERNAME);
                        }

                        break;

                    case Opcode.CLIENT_PASSWORD:
                        password = in.readLine();

                        System.out.println(chatName + " is reqesting to connect having password "
                                + password);
                        boolean result2 = userpass.get(chatName).equals(password);
                        if (result2) {
                            System.out.println(password + " is a valid password for username "
                                    + chatName);
                            out.println(Opcode.CLIENT_CONNECTED);
                        } else {
                            System.out.println(password + " is a invalid password for username "
                                    + chatName);
                            out.println(Opcode.CLIENT_INVALID_PASSWORD);
                        }

                        break;
                }
            }

            out.close();
            in.close();
            socket.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}