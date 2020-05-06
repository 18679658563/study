package com.springboot.test.nio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BIOServer {

    static byte[] b = new byte[1024];
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(9090);
            while(true){
                System.out.println(1);
                //阻塞
                Socket s = ss.accept();
                System.out.println(2);
                s.getInputStream().read(b);
                System.out.println(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
