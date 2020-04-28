package com.springboot.test.thread;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/***
 * Created with IntelliJ IDEA.
 * Description:是通过一个计数器来实现的，计数器的初始值是线程的数量。
 *          每当一个线程执行完毕后，计数器的值就-1，当计数器的值为0时，表示所有线程都执行完毕，然后在闭锁上等待的线程就可以恢复工作了
 * User: silence
 * Date: 2019-11-20
 * Time: 下午2:17
 */
public class CountDownLatchTest {

    private static final int COUNT = 10;

    private static final CountDownLatch count = new CountDownLatch(10);

    private static final ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws Exception{
        for(int i = 0 ; i < COUNT ; i++ ){
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try{
                        // 模拟业务逻辑的耗时
                        int timer = new Random().nextInt(5);
                        TimeUnit.SECONDS.sleep(timer);

                        System.out.printf("%s时完成磁盘的统计任务,耗费%d秒.\n", new Date().toString(), timer);
                        // 业务处理完成之后,计数器减一
                        count.countDown();
                    } catch (Exception e){

                    }
                }
            });
        }
        count.await();
        System.out.printf("%s时全部任务都完成,执行合并计算.\n", new Date().toString());
        executor.shutdown();



    }
}
