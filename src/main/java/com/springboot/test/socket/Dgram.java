package com.springboot.test.socket;

import java.net.DatagramPacket;
import java.net.InetAddress;

/***
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-03-15
 * Time: 下午3:30
 */
public class Dgram {

    public static DatagramPacket toDatagram(String s, InetAddress destIa,int destPort){
        byte[] buf = new byte[s.length() + 1];
        s.getBytes(0,s.length(),buf,0);
        return new DatagramPacket(buf,buf.length,destIa,destPort);
    }

    public static String toString(DatagramPacket p){
        return new String(p.getData(),0,p.getLength());
    }
}
