package com.springboot.test.thread;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * Description: 线程状态
 * User: silence
 * Date: 2018-12-04
 * Time: 下午1:58
 */
public class ThreadState{

    public static void main(String[] args){
        new Thread(new TimeWaiting(),"TimeWaitingThread").start();
        new Thread(new Waiting(),"WaitingThread").start();
        new Thread(new Blocked(),"BlockedThread-1").start();
        new Thread(new Blocked(),"BlockedThread-2").start();
    }

    static class TimeWaiting implements Runnable{

        @Override
        public void run() {
            while(true){
                SleepUtils.second(1);
            }
        }
    }

    static class Waiting implements Runnable{
        @Override
        public void run() {
            while(true){
                synchronized(Waiting.class){
                    try{
                        Waiting.class.wait();
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class Blocked implements Runnable{
        @Override
        public void run() {
            synchronized(Blocked.class){
                while(true){
                    SleepUtils.second(1);
                }
            }
        }
    }
}
class SleepUtils{

    public static final void second(long seconds){
        try{
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e){
        }
    }

}
