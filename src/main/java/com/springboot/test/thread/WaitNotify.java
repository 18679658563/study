package com.springboot.test.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * Description: wait  与  notify
 * User: silence
 * Date: 2018-12-06
 * Time: 上午11:20
 */
public class WaitNotify {

    private static Boolean flag = true;
    private static Object object = new Object();//资源对象

    public static void main(String[] args) throws Exception{
        Thread waitThread = new Thread(new Wait(), "WaitThread");
        waitThread.start();
        TimeUnit.SECONDS.sleep(1);
        Thread waitThread2 = new Thread(new Wait(), "WaitThread2");
        waitThread2.start();
        TimeUnit.SECONDS.sleep(1);
        Thread notifyThread = new Thread(new Notify(), "NotifyThread");
        notifyThread.start();
    }

    static class Wait implements Runnable{
        @Override
        public void run() {
            //加锁
            synchronized(object) {
                // 当条件不满足时,继续wait,同时释放了的object锁
                while (flag) {
                    try {
                        System.out.println(Thread.currentThread() + " flag is true. wait@ "
                                + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
                synchronized (object) {
                    System.out.println(Thread.currentThread() + " flag is false. running @ "
                            + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                    SleepUtils.second(1);
                }
        }
    }
    static class Wait2 implements Runnable{
        @Override
        public void run() {
            //加锁
            synchronized(object){
                // 当条件不满足时,继续wait,同时释放了的object锁
                    try {
                        System.out.println(Thread.currentThread() + " flag is true. wait@ "
                                + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                        object.wait(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
                synchronized (object) {
                    System.out.println(Thread.currentThread() + " flag is false. running @ "
                            +new SimpleDateFormat("HH:mm:ss").format(new Date()));
                    SleepUtils.second(1);
                }

        }
    }

    static class Notify implements Runnable{
        @Override
        public void run() {
            // 加锁,拥有lock
            synchronized(object){
                System.out.println(Thread.currentThread() + " hold object. notify @ " +
                        new SimpleDateFormat("HH:mm:ss").format(new Date()));
                object.notifyAll();
                flag = false;
                SleepUtils.second(1);
            }
            synchronized (object){
                System.out.println(Thread.currentThread() + " hold object again. sleep @ "
                        + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                SleepUtils.second(1);
            }
        }
    }

}
