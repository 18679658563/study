package com.springboot.test.thread;

/***
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-09-05
 * Time: 下午4:19
 */
public class VolatileTest implements Runnable{

    private volatile static VolatileTest volatileTest;

    private VolatileTest(){}

    public static VolatileTest getVolatileTest(){
        if(volatileTest == null){
            synchronized (VolatileTest.class){
                if(volatileTest == null){
                    volatileTest = new VolatileTest();
                }
            }
        }
        return volatileTest;
    }

    @Override
    public void run() {
        System.out.println(volatileTest);
    }

    public static void main(String[] args) {
        for(int i = 0 ; i < 500 ; i++){
            new Thread(getVolatileTest()).start();
        }
    }


}
