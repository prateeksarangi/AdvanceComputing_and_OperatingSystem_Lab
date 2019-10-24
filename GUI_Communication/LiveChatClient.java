import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

public class LiveChatClient {
    private String chatName;
    private String password;
    private String serverAddress;

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public LiveChatClient() {

        initHostName();
        runClient();
    }

    public void initHostName() {
        try {
            serverAddress = "localhost:172.27.2.161";
            if (serverAddress == null)
                System.exit(1);

            serverAddress = serverAddress.trim();
            if (serverAddress.length() == 0)
            {
                System.out.println("Server IP Address or Name can't be blank.");
                initHostName();
                return;
            }
            System.out.println("Trying to connect with server...\nServer IP Address:"
                    + serverAddress);

            InetAddress inetAddress = InetAddress.getByName(serverAddress);
            if (!inetAddress.isReachable(60000))
            {
                System.out
                        .println("Error! Unable to connect with server.\nServer IP Address may be wrong.");
                System.exit(1);
            }

            initPortNo();
        } catch (SocketException e) {
            System.out.println("Socket Exception:\n" + e);
            initHostName();
            return;
        } catch (IOException e) {
            initHostName();
            return;
        }
    }

    public void initPortNo() {
        try {

            String portNo = "1234";

            portNo = portNo.trim();
            if (portNo.length() == 0)
            {
                System.out.println("Server port No can't be blank.");
                initPortNo();
                return;
            }
            System.out.println("Trying to connect with server...\nServer Port No:" + portNo);

            socket = new Socket(serverAddress, 1234);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

        } catch (IOException e) {
            System.out.println("IO Exception:\n" + e);
            initPortNo();
            return;
        }
    }

    public void sendChatName() throws IOException {
        System.out.println("Enter user name:");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name = br.readLine();
        if (name == null)
            System.exit(1);

        chatName = name.trim();

        if (name.length() == 0) {
            System.out.println("Please enter user name.");
            sendChatName();
            return;
        }

        out.println(Opcode.CLIENT_USERNAME);
        out.println(chatName);
    }

    public void sendPassword() throws IOException {
        System.out.println("Enter password:");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name = br.readLine();
        if (name == null)
            System.exit(1);

        password = name.trim();

        if (name.length() == 0) {
            System.out.println("Please enter password.");
            sendPassword();
            return;
        }

        out.println(Opcode.CLIENT_PASSWORD);
        out.println(password);
    }

    public void runClient() {
        try {
            sendChatName();
            while (true) {
                int opcode = Integer.parseInt(in.readLine());
                switch (opcode) {
                    case Opcode.CLIENT_INVALID_USERNAME:
                        System.out.println(chatName + " is invalid user name. Try different one.");
                        sendChatName();

                        break;

                    case Opcode.CLIENT_PASSWORD:
                        sendPassword();

                        break;

                    case Opcode.CLIENT_INVALID_PASSWORD:
                        System.out.println(password + " is invalid password. Try different one.");
                        sendPassword();

                        break;

                    case Opcode.CLIENT_CONNECTED:
                        System.out.println(chatName + " is connected successfully.");

                        break;

                }
            }
        } catch (IOException e) {
            System.out.println("Client is closed...");
        }
    }


    public static void main(String args[]) {
        new LiveChatClient();
    }

}