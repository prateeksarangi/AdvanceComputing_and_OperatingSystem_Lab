import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.FileReader;

public class Java_Browser_FileInput{
    public static void main(String args[])throws IOException{
        int i = 0;
        FileReader fr = new FileReader("Client.html");
        ServerSocket server = new ServerSocket(80);
        Socket client = server.accept();
        InputStreamReader isr = new InputStreamReader(client.getInputStream());
        BufferedReader reader = new BufferedReader(isr);
        PrintStream ps = new PrintStream(client.getOutputStream());
        System.out.println("Listening to the connection at port 80 ...");
        while(true){
            while(i++ < 8)
                System.out.println(reader.readLine());
            i = 0;
            while((i = fr.read()) != -1)
                ps.print((char)i);
            fr.close();
            client.close();
        }
    }
}