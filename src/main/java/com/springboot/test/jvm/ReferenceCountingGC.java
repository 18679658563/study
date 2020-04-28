package com.springboot.test.jvm;

/***
 * Created with IntelliJ IDEA.
 * Description: 判断对象是否存活---引用计数器(给对象添加一个计数器，有人引用加1，引用失效减1，为0时表示对象不能被使用)的缺陷
 * User: silence
 * Date: 2019-04-01
 * Time: 上午11:10
 */
public class ReferenceCountingGC {

    public Object instance = null;

    private static final int _1MB = 1024 * 1024;

    /**
     * 占点内存，一边在GC日志中看清楚是否被回收过
     */
    private byte[] bigSize = new byte[2 * _1MB];

    public static void testGC(){
        ReferenceCountingGC objA = new ReferenceCountingGC();
        ReferenceCountingGC objB = new ReferenceCountingGC();
        objA.instance = objB;
        objB.instance = objA;
        objA = null;
        objB = null;
        //判断objA和objB是否呗回收
        System.gc();
    }
}
