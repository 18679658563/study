package com.springboot.test.spring;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/***
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-05-05
 * Time: 上午9:06
 */
public class MainApp {

    public static void main(String[] args){
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        context.start();

        HelloWorld obj = (HelloWorld) context.getBean("helloWorld");

        obj.getMessage();

        context.stop();

    }

}
