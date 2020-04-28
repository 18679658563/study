package com.springboot.test.jvm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/***
 * Created with IntelliJ IDEA.
 * Description: 模拟虚拟机在出现堆溢出异常
 * VM Args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 *          限制java堆是20MB 不可扩展
 * User: silence
 * Date: 2019-04-01
 * Time: 上午8:43
 */
public class HeapOOM {

    static class OOMObject{

    }

    public static void main(String[] args) throws Exception{
        List<Object> list = new ArrayList<>();
        while(true){
            list.add(new OOMObject());
        }
    }

}

