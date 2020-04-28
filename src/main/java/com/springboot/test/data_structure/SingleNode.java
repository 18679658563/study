package com.springboot.test.data_structure;

/***
 * Created with IntelliJ IDEA.
 * Description:  单链表
 * User: silence
 * Date: 2019-08-19
 * Time: 下午2:48
 */
public class SingleNode<T> {

    private class Node<T> {

        private Node next;

        private T data;

        public Node(T t) {
            this.data = t;
        }
    }

    private Node<T> head; //头节点

    private Node<T> tail; // 尾节点

    private int size = 0;//元素个数

    /**
     * 空链表
     */
    public SingleNode() {
        head = null;
        tail = null;
    }

    /**
     * 以指定数据元素来创建链表，该链表只有一个元素
     */
    public SingleNode(T data) {
        head = new Node<>(data);
        tail = head;
        size++;
    }

    /**
     * 返回元素个数
     *
     * @return
     */
    public int length() {
        return size;
    }

    /**
     * 根据索引index获取指定位置的节点
     * @param index
     * @return
     */
    public Node getNodeByIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("越界");
        }
        // 从head节点开始
        Node current = head;
        for (int i = 0; i < size && current != null; i++, current = current.next) {
            if (i == index) {
                return current;
            }
        }
        return null;
    }

    /**
     * 在尾端插入数据
     *
     * @param data
     */
    public void add(T data) {
        if (head == null) {
            head = new Node<>(data);
            tail = head;
        } else {
            Node node = new Node(data);
            tail.next = node;
            tail = node;
        }
        size++;
    }

    /**
     * 指定位置插入一个元素
     *
     * @param data
     * @param index
     */
    public void add(T data, int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("越界");
        }
        if (head == null) {
            head = new Node<>(data);
            tail = head;
        } else {
            if(index == 0){
                Node node = new Node(data);
                node.next = head;
                head = node;
            } else {
                Node node1 = getNodeByIndex(index - 1);
                Node node2 = new Node(data);
                Node node3 = node1.next;
                node1.next = node2;
                node2.next = node3;
            }
        }
        size++;
    }

    /**
     * 删除指定位置元素
     * @param index
     * @return
     */
    public T delete(int index){
        if (index > size-1 || index < 0) {
            throw new IndexOutOfBoundsException("越界");
        }
        Node<T> del = null;
        if(index == 0){
            del = head;
            head = head.next;
        } else {
            Node node = getNodeByIndex(index - 1);
            del = node.next;
            node.next = del.next;
            del.next = null;
        }
        size -- ;
        return del.data;
    }

    /**
     * 删除最后一位元素
     * @return
     */
    public T remove(){
        return  delete(size -1);
    }

    /**
     * 判断是为空
     * @return
     */
    public boolean isEmpty(){
        return size == 0;
    }

    public void clear(){
        head = null;
        tail = null;
        size = 0;
    }

    public  String toString(){
        if(isEmpty()){
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder("[");
            for(Node current = head; current != null; current = current.next){
                sb.append(current.data.toString()+",\t");
            }
            int len = sb.length();
            return sb.delete(len - 2, len).append("]").toString();
        }
    }

    public static void main(String[] args){
        SingleNode<String> nodeList = new SingleNode<>();
        for(int i = 0 ; i < 10 ; i ++){
            nodeList.add(""+i+"");
        }
        nodeList.add("23456",10);
        for(int i = 0 ; i < 11 ; i ++){
            System.out.println(nodeList.getNodeByIndex(i).data);
        }
        nodeList.delete(0);
        System.out.println(nodeList.toString());
    }

}
