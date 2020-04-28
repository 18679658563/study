package com.springboot.test.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created with IntelliJ IDEA.
 * Description: 自定义线程池
 * User: silence
 * Date: 2018-12-10
 * Time: 上午9:24
 */
public class DefaultThreadPool<Job extends Runnable> implements ThreadPools<Job> {

    //线程池最大的限制数
    private static final int MAX_WORKER_NUMBERS = 10;
    //线程池默认数量
    private static final int DEFAULT_WORKER_NUMBERS = 5;
    //线程池最小的数量
    private static final int MIN_WORKER_NUMBERS = 1;
    //工作列表，将会向里面插入工作
    private final LinkedList<Job> jobs = new LinkedList<Job>();
    //工作者列表
    private final List<Worker> workers = Collections.synchronizedList(new ArrayList<Worker>());
    //工作者线程的数量
    private int workerNum = DEFAULT_WORKER_NUMBERS;
    //线程编号的生成
    private AtomicLong threadNum = new AtomicLong();

    public DefaultThreadPool(){
        initializeWorkers(DEFAULT_WORKER_NUMBERS);
    }

    private void initializeWorkers(int num) {
        for(int i = 0 ; i < num ; i++){
            Worker worker = new Worker();
            workers.add(worker);
            Thread thread = new Thread(worker,"ThreadPool-Worker-" + threadNum.incrementAndGet());
            thread.start();
        }
    }

    @Override
    public void execute(Job job) {
        if(job != null){
            //添加一个工作，然后进行通知
            synchronized (jobs){
                jobs.addLast(job);
                jobs.notify();
            }
        }
    }

    @Override
    public void shutdown() {
        for(Worker worker :workers){
            worker.shutdown();
        }
    }

    @Override
    public void addWorkers(int num) {
        synchronized(jobs){
            //限制新增的Worker数量不能超过最大值
            if(num + this.workerNum > MAX_WORKER_NUMBERS){
                num = MAX_WORKER_NUMBERS - this.workerNum;
            }
            initializeWorkers(num);
            this.workerNum += num;
        }
    }

    @Override
    public void removeWorker(int num) throws IllegalAccessException {
        synchronized(jobs){
            if(num >= this.workerNum){
                throw new IllegalAccessException("beyond workNum");
            }
            //按照给定的数量停止Worker
            int count = 0;
            while(count < num){
                Worker worker = workers.get(count);
                if(workers.remove(worker)){
                    worker.shutdown();
                    count++;
                }
            }
            this.workerNum -= count;
        }
    }

    @Override
    public int getJobSize() {
        return jobs.size();
    }

    /***
     * 工作者，负责消费线程任务
     */
    class Worker implements Runnable{

        //是否工作
        private volatile boolean running = true;

        @Override
        public void run() {
            while(running){
                Job job = null;
                synchronized(jobs){
                    //如果工作者列表为空，那么就wait
                    while(jobs.isEmpty()){
                        try {
                            jobs.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    //取出一个job
                    job = jobs.removeFirst();
                }
                if(job != null){
                    try{
                        job.run();
                    } catch (Exception w){
                        //忽略job执行中的Exception
                    }
                }
            }
        }
        public void shutdown(){
            running = false;
        }
    }

}

interface ThreadPools<Job extends  Runnable>{
    // 执行一个Job,这个Job需要实现Runnable
    void execute(Job job);
    // 关闭线程池
    void shutdown();
    // 增加工作者线程
    void addWorkers(int num);
    // 减少工作者线程
    void removeWorker(int num) throws IllegalAccessException;
    // 得到正在等待执行的任务数量
    int getJobSize();
}
