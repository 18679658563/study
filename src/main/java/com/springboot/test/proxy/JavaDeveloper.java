package com.springboot.test.proxy;

/***
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2020-01-08
 * Time: 下午4:00
 */
public class JavaDeveloper implements Develop {

    private String name;

    JavaDeveloper(String name){
        this.name = name;
    }


    @Override
    public void code() {
        System.out.println(this.name + "is coding java");
    }

    @Override
    public void debug() {
        System.out.println(this.name + "is debugging java");
    }
}

interface Develop{

    void code();

    void debug();
}
