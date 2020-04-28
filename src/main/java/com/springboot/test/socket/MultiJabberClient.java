package com.springboot.test.socket;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/***
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-03-15
 * Time: 下午1:47
 */
public class MultiJabberClient {
    static final int MAX_THREADS = 40;
    public static void main(String[] args) throws IOException, InterruptedException {
        InetAddress addr = InetAddress.getByName(null);
        while(true) {
            if(JabberClientThread.threadCount() < MAX_THREADS)
                new JabberClientThread(addr);
            Thread.currentThread().sleep(100);
        }
    }
}

class JabberClientThread extends Thread {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private static int counter = 0;
    private int id = counter++;
    private static int threadcount = 0;
    public static int threadCount() {
        return threadcount;
    }
    public JabberClientThread(InetAddress addr) {
        System.out.println("Making client " + id);
        threadcount++;
        try {
            socket =
                    new Socket(addr, MultiJabberServer.PORT);
        } catch(IOException e) {
        }
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            start();
        } catch(IOException e) {
            try {
                socket.close();
            } catch(IOException e2) {}
        }
    }
    public void run() {
        try {
            for(int i = 0; i < 25; i++) {
                out.println("Client " + id + ": " + i);
                String str = in.readLine();
                System.out.println(str);
            }
            out.println("END");
        } catch(IOException e) {
        } finally {
            try {
                socket.close();
            } catch(IOException e) {}
            threadcount--;
        }
    }
}

