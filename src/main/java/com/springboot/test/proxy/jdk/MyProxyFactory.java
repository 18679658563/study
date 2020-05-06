package com.springboot.test.proxy.jdk;

import java.lang.reflect.Proxy;

/***
 * jdk动态代理生产代理对象的工厂
 */
public class MyProxyFactory {

    public static Object getProxy(Object target){
        MyInvocationHandle myInvocationHandle = new MyInvocationHandle();
        myInvocationHandle.setTarget(target);
        Object proxy = Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),myInvocationHandle);
        return proxy;
    }

    public static void main(String[] args) {
        IDog dog = new GunDog();
        IDog iDog = (IDog) MyProxyFactory.getProxy(dog);
        iDog.run();
    }
}
