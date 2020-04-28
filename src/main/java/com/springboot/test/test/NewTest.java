package com.springboot.test.test;

/***
 * Created with Int elliJ IDEA.
 * Description:  初始化
 * User: silence
 * Date: 2020-01-02
 * Time: 上午9:12
 */
public class NewTest {

    static {
        System.out.println("new test: " );
       // System.out.println(i); //  非法前向引用
    }

    public static int i = 0;

    NewTest(){
        System.out.println(11111111);
    }

}

class TestTest extends NewTest {

    static {
        System.out.println("test test: ");
    }

    TestTest(){
        System.out.println(222222222);
    }
}

class Test2{
    public static void main(String[] args) {
        // TestTest n = new TestTest();
        System.out.println(TestTest.i);
    }
}
