package com.springboot.test.thread;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description: 一位农民计划将两种食物混合在一起，为农场里的动物提供低成本饲料。
 *              一袋食物A价格10美元，含有40单位蛋白质，20单位矿物质和10单位维生素。
 *              食品B的价格为12美元，含有30单位的蛋白质，20单位的矿物质和30单位的维生素。
 *              动物每天要消耗多少袋食物A和B，以最低要求满足每日150单位的蛋白质，90单位的矿物质和60维的维生素，成本最低？
 * User: silence
 * Date: 2018-10-26
 * Time: 下午4:38
 */
public class LessProject {

    public static void main(String[] args){
        LessProject.less();
    }

    public static void less(){
        Map<Integer,String> map = new HashMap<>();
        for(int a = 0 ; a < 10 ; a++){
            for(int b = 0 ; b < 10 ; b++){
                if((40*a + 30*b) >= 150 && 20*(a +b) >= 90 && (10*a+30*b) >= 60){
                    Integer price = 10*a +12*b;
                    String value = "a=" + a + ";b=" + b + ",成本为：" + price + "\n消耗蛋白质：" + (40*a + 30*b) + "消耗矿物质：" + 20*(a + b) + "消耗维生素：" + (10*a + 30*b);
                    map.put(price,value);
                    break;
                }
            }
        }
        Integer money = 0;
        for(Integer key : map.keySet()){
            if(money == 0){
                money = key;
            }
            money = money > key ? key : money;
        }
        System.out.println(map.get(money));
    }

}
