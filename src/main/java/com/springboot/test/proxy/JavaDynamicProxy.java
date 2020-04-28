package com.springboot.test.proxy;

import java.lang.reflect.Proxy;

/***
 * Created with IntelliJ IDEA.
 * Description: 动态代理
 * User: silence
 * Date: 2020-01-08
 * Time: 下午4:03
 */
public class JavaDynamicProxy {

    public static void main(String[] args) {
        JavaDeveloper zack = new JavaDeveloper("zack");

        Develop develop = (Develop) Proxy.newProxyInstance(zack.getClass().getClassLoader(),zack.getClass().getInterfaces(),(proxy, method, s) -> {
            if("code".equals(method.getName())){
                System.out.println("proxy is coding");
                return method.invoke(zack,s);
            }
            if("debug".equals(method.getName())){
                System.out.println("proxy is debugging");
                return null;
            }
            return null;
        });
        develop.code();
        develop.debug();

    }
}
