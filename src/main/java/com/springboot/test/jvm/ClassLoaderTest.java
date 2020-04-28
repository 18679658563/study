package com.springboot.test.jvm;

import java.io.IOException;
import java.io.InputStream;

/***
 * Created with IntelliJ IDEA.
 * Description:  类加载器与instanceof关键字演示
 * User: silence
 * Date: 2019-04-17
 * Time: 上午8:52
 */
public class ClassLoaderTest {

    public static void main(String[] args) throws Exception{

        ClassLoader myLoader = new ClassLoader() {

            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    System.out.println("1"+fileName);
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if(is == null){
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name,b,0,b.length);
                } catch (IOException e){
                    throw new ClassNotFoundException(name);
                }
            }
        };

        Object o = myLoader.loadClass("com.springboot.test.jvm.ClassLoaderTest").newInstance();
        System.out.println("2"+o.getClass());
        System.out.println("3"+(o instanceof ClassLoaderTest));
    }

}



