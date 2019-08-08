import java.io.*;
import java.net.*;

class Server{
    static ServerSocket ss;
    static Socket s;
    static BufferedReader br;
    public static void main(String args[]) throws IOException{
        ss = new ServerSocket(2000);
        s = ss.accept();
        br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        System.out.println(br.readLine());
    }
}