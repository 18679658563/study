package com.springboot.test.thread;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/***
 * Created with IntelliJ IDEA.
 * Description:  模拟抢单
 * User: silence
 * Date: 2019-07-08
 * Time: 上午10:33
 */
public class SellTest {

    private static SellTest sell = new SellTest();

    public static Integer count = 0;

    private SellTest(){}

    public static SellTest getInstance(String id){
//        if(sell == null) {
//            synchronized (SellTest.class) {
//                if (sell == null) {
//                    sell = new SellTest();
//                }
//            }
//        }
        SellTest.getMap(id);
        return sell;
    }

    private static Map<String,Integer> map = new ConcurrentHashMap();

    private static Map getMap(String id){
        if(!map.containsKey(id)){
            //根据id查询库,将结果存入Map
            map.put("1",10000);
        }
        return map;
    }


    public synchronized Integer sell(String id){
        if(map.get(id) > 0){
            // do something  -->map
            map.put(id,map.get(id)-1);
            count++;
        }
        return map.get(id);
    }

    public static Map m = new ConcurrentHashMap();

    public static void main(String[] args)throws Exception{
        for(int i = 0 ; i < 100000; i ++){
            new Thread() {
                public void run() {
                    int s = SellTest.getInstance("1").sell("1");
                    if(s > 0){System.out.println(s);}
                    m.put(UUID.randomUUID().toString()+UUID.randomUUID(),s);
                }
            }.start();
        }
        while(Thread.activeCount() > 2){
           Thread.yield();
        }
        System.out.println(m.keySet().size());
        System.out.println(count);
    }

}
