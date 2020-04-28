package com.springboot.test.jvm;

import com.springboot.entity.User;

/***
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-12-11
 * Time: 上午10:39
 */
public class TaoTest {

    public static void test() throws Exception{
        for(int i = 0 ; i < 100000; i++){
            User user = new User();
        }

    }
    public static void t() throws Exception{
        test();
        Thread.sleep(60*1000);
    }


    public User test2(){
        User user = null;
        for(int i = 0 ; i < 100000; i++){
            user = new User();
        }
        return user;
    }

    public static void main(String[] args) throws Exception {
        TaoTest.t();
    }

}
