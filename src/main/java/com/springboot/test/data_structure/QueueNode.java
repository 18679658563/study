package com.springboot.test.data_structure;

/***
 * Created with IntelliJ IDEA.
 * Description: 链式队列
 * User: silence
 * Date: 2019-08-21
 * Time: 上午10:26
 */
public class QueueNode<T> {

    private class Node<T>{
        private T data;
        private Node<T> next;
        public Node(){
            this.data=null;
            this.next=null;

        }
        public Node(T data){
            this.data=data;
            this.next=null;
        }
        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node<T> front; //队首

    private Node<T> rear; //队尾

    public QueueNode(){
        front = rear = null;
    }

    //清空
    public void clear(){
        front = rear = null;
    }

    //判空
    public boolean isEmpty(){
        return front == null;
    }

    //长度
    public int length(){
        Node<T> node = front;
        int length = 0 ;
        while(node != null){
            node = node.next;
            length ++;
        }
        return length;
    }

    //队首元素
    public T peek(){
        if(isEmpty()){return null;}
        return front.data;
    }

    //入列
    public void push(T t){
        Node<T> node = new Node<>(t);
        if(isEmpty()){
            front = rear = node;
        } else {
            rear.next = node;
            rear = node;
        }
    }

    //出列
    public T poll(){
        if(isEmpty()){
            return null;
        }
        Node<T> node = front;
        front = node.next;
        if(node == rear){
            rear = null;
        }
        return node.data;
    }



}
