package com.springboot.test.thread;


/**
 * Created with IntelliJ IDEA.
 * Description: Daemon线程
 * User: silence
 * Date: 2018-12-04
 * Time: 下午3:30
 */
public class Daemon {
    public static void main(String[] args){
        Thread thread = new Thread(new DaemonThread(),"DaemonThread");
        thread.setDaemon(true);
        thread.start();
    }
    static class DaemonThread implements Runnable{
        @Override
        public void run() {
            try{
                SleepUtils.second(1);
            }finally {
                System.out.println("DaemonThread finally run.");
            }
        }
    }
}
