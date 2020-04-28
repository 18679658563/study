package com.springboot.test.jvm;

/***
 * Created with IntelliJ IDEA.
 * Description: 被动使用类字段演示一
 *              通过子类引用弗雷德静态字段，不会导致子类初始化
 * User: silence
 * Date: 2019-04-12
 * Time: 上午9:16
 */
public class SuperClass {

    static{
        System.out.println("SuperClass init");
    }

    public final static int value = 1234;
}

class SubClass extends SuperClass{

    static{
        System.out.println("SubClass init");
    }
}

class NotInitialization{

    public static void main(String[] args){
        System.out.println(SubClass.value);//SuperClass init(value未被final修饰)  \n 1234
    }
}

