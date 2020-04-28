package com.springboot.test.socket;

import java.lang.Thread;
import java.net.*;
import java.io.*;

/***
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-03-15
 * Time: 下午3:46
 */
public class ChatterClient extends Thread {
    private DatagramSocket s;
    private InetAddress hostAddress;
    private byte[] buf = new byte[1000];
    private DatagramPacket dp = new DatagramPacket(buf, buf.length);
    private int id;
    public ChatterClient(int identifier) {
        id = identifier;
        try {
            s = new DatagramSocket();
            hostAddress = InetAddress.getByName("localhost");
        } catch(UnknownHostException e) {
            System.err.println("Cannot find host");
            System.exit(1);
        } catch(SocketException e) {
            System.err.println("Can't open socket");
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println("ChatterClient starting");
    }
    public void run() {
        try {
            for(int i = 0; i < 25; i++) {
                String outMessage = "Client #" + id + ", message #" + i;
                s.send(Dgram.toDatagram(outMessage, hostAddress, ChatterServer.INPORT));
                s.receive(dp);
                String rcvd = "Client #" + id +
                              ", rcvd from " +
                              dp.getAddress() + ", " +
                              dp.getPort() + ": " +
                              Dgram.toString(dp);
                System.out.println(rcvd);
            }
        } catch(IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    public static void main(String[] args){
        for(int i = 0; i < 10; i++)
             new ChatterClient(i).start();

    }
}
