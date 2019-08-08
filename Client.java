import java.io.*;
import java.net.*;

class Client{
    static Socket s;
    static PrintStream ps;
    public static void main(String args[]) throws IOException{        
        s = new Socket("localhost", 2000);
        ps = new PrintStream(s.getOutputStream());
        ps.println("Hello Server");
    }
}