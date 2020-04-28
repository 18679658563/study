package com.springboot.test.jvm;

import java.util.ArrayList;
import java.util.List;

/***
 * Created with IntelliJ IDEA.
 * Description: 运行时常量池导致的内存溢出异常
 *
 *              args : -XX:PermSize=10M -XX:MaxPermSize=10M
 * User: silence
 * Date: 2019-04-01
 * Time: 上午9:39
 */
public class RuntimeConstantPoolOOM {

    public static void main(String[] args){
        //使用list保持着常量池引用，避免full  gc回收常量池行为
        List<String> list = new ArrayList<>();
        //10m的permSize在integer范围内足够产生oom
        int i = 0 ;
        while(true){
            list.add(String.valueOf(i++).intern());
        }

    }

}
