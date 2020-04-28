package com.springboot.test.thread;

import java.math.BigDecimal;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

/***
 * Created with IntelliJ IDEA.
 * Description: 创建任意数量的线程,并通过为每个线程分配一个独一无二的编号(由一个静态变量产生),从而对不同的线程进行跟踪
 * User: silence
 * Date: 2019-03-13
 * Time: 上午10:08
 */
public class SimpleThread extends Thread {

    private int countDown = 5;

    private int threadNumber;

    private static int threadCount = 0;

    public SimpleThread(){
        threadNumber = ++threadCount;
        System.out.println("Making " + threadNumber);
    }

    public void run(){
        while(true){
            System.out.println("Thread " + threadNumber + "(" + countDown + ")");
            if(--countDown == 0) {
                return;
            }
        }
    }

    public static void main(String[] args){
//        for(int i = 0 ; i < 5 ; i ++){
//            new SimpleThread().start();
//        }
        System.out.println("All Threads Started");
    }

}
