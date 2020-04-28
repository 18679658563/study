package com.springboot.test.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created with IntelliJ IDEA.
 * Description: 读写锁的使用
 * User: silence
 * Date: 2018-12-17
 * Time: 上午10:15
 */
public class Cache {

    static Map<String,Object> map = new HashMap<String,Object>();
    static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    static Lock read = rwl.readLock();
    static Lock write = rwl.writeLock();

    //获取一个key对应的value
    public static final  Object get(String key){

        read.lock();
        try{
            return  map.get(key);
        } finally {
            read.unlock();
        }
    }

    //设置key对应的value，并返回旧的value
    public static final Object put(String key,Object value){
        write.lock();
        try{
            return map.put(key,value);
        } finally {
            write.unlock();
        }
    }

    //清空所有内容
    public static final void clear() {
        write.lock();
        try {
            map.clear();
        } finally {
            write.unlock();
        }

    }


}
