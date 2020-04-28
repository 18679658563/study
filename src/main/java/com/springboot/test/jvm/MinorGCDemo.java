package com.springboot.test.jvm;

/***
 * Created with IntelliJ IDEA.
 * Description:定义了4个字节数组对象，3个2MB大小、1个4MB大小，
 *              通过-Xms20M -Xmx20M -Xmn10M 三个参数限制了Java堆大小为 20M ，不可扩展，其中的 10MB 分配给新生代，剩下 10MB 分配给老年代
 *              -XX:SurvivorRatio=8 新生代 Eden 与 Survivor 区空间比例是 8:1:1
 * User: silence
 * Date: 2019-10-17
 * Time: 下午2:49
 */
public class MinorGCDemo {

    private static final int _1MB = 1024 * 1024;
    /**
     * VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     * */
    public static void testAllocation() {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[4 * _1MB]; //出现一次 Minor GC
        //这次GC发生的原因是给allocation4对象分配内存的时候，发现Eden区已经被占用了6MB，剩余空间已经不足以分配4MB的内存，
        // 因此发生了MinorGC。GC期间有发现已有的3个2MB大小的对象已经无法全部放入Survivor空间（只有1MB大小）,所以只好通过分配担保机制提前将这三个对象转移到老年代去了。
    }

    public static void main(String[] args) {
        MinorGCDemo.testAllocation();
    }

}
