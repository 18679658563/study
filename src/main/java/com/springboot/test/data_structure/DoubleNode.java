package com.springboot.test.data_structure;

/***
 * Created with IntelliJ IDEA.
 * Description: 双向链表
 * User: silence
 * Date: 2019-08-20
 * Time: 上午8:33
 */
public class DoubleNode<T> {

    private class Node<T> {

        private Node<T> next; //下一个

        private Node<T> pre; //上一个

        private T data;

        public Node(T t,Node<T> next,Node<T> pre) {
            this.data = t;
            this.next = next;
            this.pre = pre;
        }
    }

    private Node<T> first;

    private Node<T> last;

    private int size;

    public DoubleNode(){
        first = null;
        last = null;
        size = 0;
    }
    /**
     * 判断是否为空
     * @return
     */
    public boolean isEmpty(){
        return size == 0;
    }
    /**
     * 长度
     * @return
     */
    public int length(){
        return size;
    }
    /**
     * 获取首位
     * @return
     */
    public Node<T> getFirst(){return first;}
    /**
     * 获取尾位
     * @return
     */
    public Node<T> getLast(){return last;}
    /**
     * 根据具体的值查询链表
     * @param value
     * @return
     */
    public Node<T> getNode(T value){
        if(isEmpty()){
            return null;
        }
        Node<T> node = first;
        if(node.data == value){
            return node;
        }
        while(node.next.data != value){
            node = node.next;
            if(node == null){
                return null;
            }
        }
        return node;
    }
    /**
     * 根据下标查询链表
     * @param index
     * @return
     */
    public Node<T> getIndex(int index){
        if(index > size - 1 || index < 0){
            throw new IndexOutOfBoundsException("越界");
        }
        if(index <= size/2){
            Node<T> tail = first;
            for(int i = 0 ; i <= size/2 && tail.next != null; i++, tail = tail.next){
               if(i == index){
                   return tail;
               }
            }
        }else{
            Node<T> tail = last;
            for(int i = size -1 ; i > size/2 && tail.pre != null ; i--, tail = tail.pre){
                if(i == index){
                    return tail;
                }
            }
        }
        return null;
    }

    /**
     * 尾端插入
     * @param value
     */
    public void add(T value){
        if(isEmpty()){
            first = new Node<>(value,null,null);
            last = first;
        }else{
            Node<T> node = new Node<>(value,null,last);
            last.next = node;
            last = node;
        }
        size ++;
    }
    /**
     * 下标插入
     */
    public void add(int index ,T value){
        if(index > size  || index < 0){
            throw new IndexOutOfBoundsException("越界");
        }
        if(isEmpty()){
            first = new Node<>(value,null,null);
            last = first;
        }else{
           if (index == 0) {
               Node<T> node = new Node<>(value, first,null);
               first.pre = node;
               first = node;
           } else if(index == size){
               Node<T> node = new Node<>(value,null,last);
               last.next = node;
               last = node;
           }else {
               Node<T> prev = getIndex(index-1);
               Node<T> next = getIndex(index);
               Node<T> node = new Node<>(value, next,prev);
               prev.next = node;
               next.pre = node;
           }
        }
        size ++;
    }
    /**
     * 删除最后一位
     * @return
     */
    public T delete(){
        return delete(size -1);
    }
    /**
     * 删除指定下标
     * @return
     */
    public T delete(int index){
        if(index > size - 1 || index < 0){
            throw new IndexOutOfBoundsException("越界");
        }
        Node<T> node = null;
        if(index == size -1){
            node = last;
            last = last.pre;
            last.next = null;
        } else if(index == 0){
            node = first;
            first = first.next;
            first.pre = null;
        } else {
            node = getIndex(index);
            Node<T> pre = node.pre;
            Node<T> next = node.next;
            pre.next = next;
            next.pre = pre;
            node.next = null;
            node.pre = null;
        }
        size --;
        return node.data;
    }

    public void clear(){
        first = null;
        last = null;
        size = 0;
    }

    public String toString(){
        if(isEmpty()){
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        for(Node<T> node = first;node != null ; node =node.next){
            sb.append(node.data+",\t");
        }
        return sb.append("]").toString();
    }

    public static void main(String[] args){
        DoubleNode<Integer> node = new DoubleNode<>();
        for(int i = 0 ; i < 10 ; i ++){
            node.add(i);
        }
        node.add(10,567);
        node.clear();
        System.out.println(node.toString());
    }

}
