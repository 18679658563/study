package com.springboot.test.socket;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/***
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-03-14
 * Time: 下午4:34
 */
public class JabberClient {


    public static void main(String[] args) throws IOException {
        InetAddress address = InetAddress.getByName(null);
        System.out.println("address = " + address);
        Socket socket = new Socket(address,JabberServer.PORT);
        try{
            System.out.println("Socker = " + socket);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
            for(int i = 0 ; i < 10 ; i ++){
                out.println("howdy " + i );
                String str = in.readLine();
                System.out.println(str);
            }
            out.println("END");
        }finally {
            System.out.println("close.....");
            socket.close();

        }
    }
}
