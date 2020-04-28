package com.springboot.test.jvm;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/***
 * Created with IntelliJ IDEA.
 * Description: 借助CGLib使方法区出现内存溢出异常
 *
 *              args: -XX:PermSize=10M -XX:MaxPermSize=10M
 * User: silence
 * Date: 2019-04-01
 * Time: 上午10:55
 */
public class JVMMethodAreaOOM {

    static class OOMObject{

    }

    public static void main(String[] args){
        while(true){
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invokeSuper(o,objects);
                }
            });
            enhancer.create();
        }
    }

}
