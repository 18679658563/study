package com.springboot.test.thread;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/***
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-03-13
 * Time: 上午10:18
 */
public class DaemonThread extends Thread {

    private static final int SIZE = 10;

    private Thread[] t = new Thread[SIZE];

    public DaemonThread(){
        setDaemon(true);
        start();
    }
    public void run(){
        for(int i = 0; i < SIZE; i++){
            t[i] = new DaemonSpawn(i);
        }
        for(int i = 0; i < SIZE; i++){
            System.out.println("t[" + i + "].isDaemon() = " + t[i].isDaemon());
        }
        while(true){
            yield();
        }
    }
}
class DaemonSpawn extends Thread{
    public DaemonSpawn(int i){
        System.out.println("DaemonSpawn " + i + " started ");
        start();
    }
    public void run(){
        while(true){
            yield();
        }
    }
}
class Daemons{
    public static void main(String[] args){
        Thread d = new DaemonThread();
        System.out.println("d.isDaemon() = " + d.isDaemon());
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Waiting for CR");
        try{
//            stdin.readLine();
            Thread.sleep(50);
        }catch(Exception e){}
    }
}

