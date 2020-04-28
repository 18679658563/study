package com.springboot.test.data_structure;

/***
 * Created with IntelliJ IDEA.
 * Description: 顺序队列
 * User: silence
 * Date: 2019-08-21
 * Time: 上午9:15
 */
public class QueueArray<T> implements Queue<T>{

    private T[] data; //元素集

    private int size; //元素个数

    private int header; //第一个对象的位置

    private int rear; //当前对象的位置

    public QueueArray(){
        data = (T[])new Object[10];
        size = 0 ;
        header = 0;
        rear = 0;
    }

    //入队列
    @Override
    public void add(T t) {
        if(isFull()){
            resize();
            header = 0;
        }
        rear = (header + size) % data.length;
        data[rear] = t;
        size ++;
    }
    //出队列
    @Override
    public T remove() {
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }
        T t =  data[header];
        data[header] = null;
        header = (header+1)%data.length;
        size --;
        return t;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == data.length;
    }
    //首元素
    @Override
    public T getHeader() {
        if(isEmpty()){ throw new IndexOutOfBoundsException("队列为空");}
        return data[header];
    }

    //扩容
    public void resize(){
        T[] newData = (T[])new Object[data.length*2];
        System.arraycopy(data,0,newData,0,data.length);
        data = newData;
        newData = null;
    }

}
interface Queue<T>{

    void add(T t);

    T remove();

    int size();

    boolean isEmpty();

    boolean isFull();

    T getHeader();
}
