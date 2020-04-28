package com.springboot.test.thread;

/***
 * Created with IntelliJ IDEA.
 * Description: 对于普通同步方法，锁是当前实例对象。
 *             ·对于静态同步方法，锁是当前类的Class对象。
 *             ·对于同步方法块，锁是Synchonized括号里配置的对象。
 * User: silence
 * Date: 2019-09-03
 * Time: 上午8:22
 */
public class SynchronizedTest implements Runnable{

    public static Object i = 9;

    public static void get(){
        i = (int)i-1;
        System.out.println(i);
    }

    @Override
    public void run(){
        try{
            synchronized (i){
                System.out.println("2222222222222");
                Thread.sleep(1000);
                get();
            }
        }catch (Exception e){
        }
    }

    public static void main(String[] args)throws Exception {
        SynchronizedTest s = new SynchronizedTest();
        for(int i = 0 ; i < 10 ;i++){
            new Thread(s).start();
        }
        Thread.sleep(500);
        System.out.println(1234);
        SynchronizedTest.get();
        System.out.println(1234);
    }
}
