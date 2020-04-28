package com.springboot.test.socket;

import java.net.InetAddress;

/***
 * Created with IntelliJ IDEA.
 * Description: 利用 InetAddress.getByName()来产生 IP 地址
 * User: silence
 * Date: 2019-03-14
 * Time: 下午1:56
 */
public class WhoAml {

    public static void main(String[] args) throws Exception{
        InetAddress a = InetAddress.getByName("localhost");
        System.out.println(a);
    }

}
