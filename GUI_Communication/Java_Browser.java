import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Java_Browser{
    public static void main(String args[])throws IOException{
        int i = 0;
        ServerSocket server = new ServerSocket(80);
        Socket client = server.accept();
        InputStreamReader isr = new InputStreamReader(client.getInputStream());
        BufferedReader reader = new BufferedReader(isr);
        PrintStream ps = new PrintStream(client.getOutputStream());
        System.out.println("Listening to the connection at port 80 ...");
        while(true){
            while(i++ < 8)
                System.out.println(reader.readLine());
                ps.println("<!DOCTYPE html><html><title>ServerProgram</title><body><h1>Hello World</h1></body></html>");
                client.close();
        }
    }
}