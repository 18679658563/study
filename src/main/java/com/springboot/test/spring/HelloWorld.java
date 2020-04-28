package com.springboot.test.spring;

/***
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-05-05
 * Time: 上午9:00
 */
public class HelloWorld {

    private String message;

    public void getMessage() {
        System.out.println("Your Message : " + message);
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
