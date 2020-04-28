package com.springboot.test.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/***
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-11-01
 * Time: 下午2:06
 */
public class ExecutorsTest {

    public static void main(String[] args)throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        System.out.println(executorService.submit(new Test()).get());
        executorService.shutdown();
    }

    public static class Test implements Callable{

        @Override
        public Object call() throws Exception {
            Thread.sleep(1000);
            return 11;
        }
    }
}
