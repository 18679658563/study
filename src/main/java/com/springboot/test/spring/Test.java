package com.springboot.test.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/***
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2020-01-10
 * Time: 下午2:49
 */
public class Test {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(HelloWorld.class);
    }
}
