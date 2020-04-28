package com.springboot.test.interviewQuestion;

/***
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-08-09
 * Time: 上午10:26
 */
public class Test {

    @org.junit.Test
    public void test(){
        int x = 0x61c88647;
        while(x != 0){
            int y = x % 2;
            x = x / 2;
            System.out.println(y);
        }
        System.out.println("+++++"+0x61c88647);
        int len = 32;
        int key = 0 ;
        for (int i = 0 ; i < len ;i++){
            key += 0x61c88647;
            System.out.print((key & (len -1))+"\t");
        }
    }
}
