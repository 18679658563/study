package com.springboot.test.data_structure;

/***
 * Created with IntelliJ IDEA.
 * Description: 循环队列
 * User: silence
 * Date: 2019-08-21
 * Time: 上午9:41
 */
public class QueueCircle<T> implements IQueue<T>{

    private T[] data;
    private int maxSize;
    private int header;//首元素，队列非空，队首元素
    private int rear; // 尾元素／当前元素，队列非空，队尾下一元素

    public QueueCircle(int maxSize){
        header = rear = 0;
        data = (T[])new Object[maxSize];
        this.maxSize = maxSize;
    }

    @Override
    public void clear() {
        rear = header = 0;
        data = (T[])new Object[maxSize];
    }

    @Override
    public boolean isEmpty() {
        return rear == header;
    }

    @Override
    public int length() {
        return (rear - header + maxSize) % maxSize;
    }

    @Override
    public T getHeader() {
        return data[header];
    }

    //入队列
    @Override
    public boolean offer(T t) {
        if((rear + 1) % maxSize == header){
            return false;
        }
        data[rear] = t;
        rear= (rear+1)%maxSize;
        return true;
    }

    @Override
    public T poll() {
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }
        T t = data[header];
        data[header] = null;
        header = (header+1)%maxSize;
        return t;
    }
}

interface IQueue<T>{
    void clear();
    boolean isEmpty();
    int length();
    T getHeader();
    boolean offer(T t);
    T poll();
}
