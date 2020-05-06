package com.springboot.test.nio;

import java.io.IOException;
import java.net.Socket;

public class BIOClient {

    static byte[] b = new byte[1024];
    public static void main(String[] args) {
        try {
            Socket ss = new Socket("127.0.0.1",9090);
            ss.getOutputStream().write("hello world".getBytes());
            ss.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
