package com.springboot.test.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/***
 *  jdk 动态代理
 */
public class MyInvocationHandle implements InvocationHandler {

    private Object target;

    public void setTarget(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        DogUtil.method1();
        method.invoke(target,args);
        DogUtil.method2();
        return null;
    }
}
