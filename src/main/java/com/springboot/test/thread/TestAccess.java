package com.springboot.test.thread;

/***
 * Created with IntelliJ IDEA.
 * Description: 展示了位于一个“叶子组”内的线程能修改它所在线程组树的所有线程的优先级,同时还能为这个“树”内的所有线程都调用一个方法
 * User: silence
 * Date: 2019-03-13
 * Time: 下午3:08
 */
public class TestAccess {

    public static void main(String[] args){
        ThreadGroup x = new ThreadGroup("x");
        ThreadGroup y = new ThreadGroup(x,"y");
        ThreadGroup z = new ThreadGroup(y,"z");
        Thread one = new TestThread1(x,"one");
        Thread two = new TestThread2(z,"two");
    }
}

class TestThread1 extends Thread{
    private int i;

    TestThread1(ThreadGroup g,String name){
        super(g,name);
    }
    void f(){
        i++;
        System.out.println(getName() + "f()");
    }
}
class TestThread2 extends TestThread1 {

    TestThread2(ThreadGroup g ,String name){
        super(g,name);
        start();
    }
    public void run(){
        ThreadGroup g = getThreadGroup().getParent().getParent();
        g.list();
        Thread[] all = new Thread[g.activeCount()];
        g.enumerate(all);
        for(int i = 0; i < all.length; i++){
            all[i].setPriority(Thread.MIN_PRIORITY);
            ((TestThread1)all[i]).f();
        }
        g.list();
    }

}
