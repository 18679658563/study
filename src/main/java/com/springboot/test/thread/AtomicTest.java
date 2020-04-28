package com.springboot.test.thread;

import java.util.concurrent.atomic.AtomicReference;

/***
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-11-19
 * Time: 上午10:44
 */
public class AtomicTest {

    private static AtomicReference<Integer> atomic = new AtomicReference<>(0);

    public static void main(String[] args) {
        atomic.compareAndSet(0,1);
        atomic.compareAndSet(1,2);
        atomic.compareAndSet(0,3);
        System.out.println(atomic.get());
    }

//    //更新变量的值
//    private static AtomicIntegerFieldUpdater<AtomicTest> updater =
//            AtomicIntegerFieldUpdater.newUpdater(AtomicTest.class, "count");
//    @Getter
//    private volatile int count = 100;
//
//    public static void main(String[] args) {
//
//        AtomicTest example5 = new AtomicTest();
//
//        if (updater.compareAndSet(example5, 100, 120)) {
//            System.out.println(example5.getCount());
//        }
//
//        if (updater.compareAndSet(example5, 120, 1210)) {
//            System.out.println(example5.getCount());
//        } else {
//            System.out.println(example5.getCount());
//        }
//    }

}
