package com.springboot.test.thread;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2018-12-06
 * Time: 上午8:37
 */
public class Synchronized {
    public static void main(String[] args) {
// 对Synchronized Class对象进行加锁
        synchronized (Synchronized.class) {
        }// 静态同步方法,对Synchronized Class对象进行加锁
        m();
    }
    public static synchronized void m() {
    }
}
