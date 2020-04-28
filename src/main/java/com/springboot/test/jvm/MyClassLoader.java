package com.springboot.test.jvm;

import java.io.FileInputStream;
import java.lang.reflect.Method;

/***
 * Created with IntelliJ IDEA.
 * Description: 自定义类加载器
 * User: silence
 * Date: 2020-01-02
 * Time: 下午4:10
 */
public class MyClassLoader {

    static class MyClassLoaderTest extends ClassLoader {
        private String classPath;
        public MyClassLoaderTest(String classPath) {
            this.classPath = classPath;
        }
        private byte[] loadByte(String name) throws Exception {
            name = name.replaceAll("\\.", "/");
            FileInputStream fis = new FileInputStream(classPath + "/" + name
                    + ".class");
            int len = fis.available();
            byte[] data = new byte[len];
            fis.read(data);
            fis.close();
            return data;
        }

        protected Class<?> findClass(String name) throws ClassNotFoundException {
            try {
                byte[] data = loadByte(name);
                return defineClass(name, data, 0, data.length);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ClassNotFoundException();
            }
        }
    };

    public static void main(String args[]) throws Exception {
        MyClassLoaderTest classLoader = new MyClassLoaderTest("/home/silence/workspace/springboot");
        Class clazz = classLoader.loadClass("com.springboot.test.javase.Test");
        Object obj = clazz.newInstance();
        Method helloMethod = clazz.getDeclaredMethod("hello", null);
        helloMethod.invoke(obj, null);
    }
}
