package com.springboot.test.data_structure;

/***
 * Created with IntelliJ IDEA.
 * Description: 循环双向链表
 * User: silence
 * Date: 2019-08-20
 * Time: 上午10:41
 */
public class XunHuanNode<T> {

    private class Node<T> {
        Node<T> next;
        Node<T> prev;
        T data;

        public Node(T data, Node<T> next, Node<T> prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }

    private Node<T> header;
    private int size;

    public XunHuanNode() {
        Node<T> node = new Node<>(null,null,null);
        header = node;
        header.next = header;
        header.prev = header;
        size = 0;
    }

    public int length() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 根据具体的值查询链表
     * @param value
     * @return
     */
    public Node<T> getNode(T value) {
        if (isEmpty()) {
            return null;
        }
        Node<T> node = header;
        if (node.data == value) {
            return node;
        }
        while (node.next != header) {
            if (node.data == value) {
                return node;
            }
            node = node.next;
        }
        return null;
    }

    /**
     * 根据下标查询链表
     * @param index
     * @return
     */
    public Node<T> getIndex(int index) {
        if (index > size - 1 || index < 0) {
            throw new IndexOutOfBoundsException("越界");
        }
        Node<T> tail = header;
        for (int i = 0; i < size; i++, tail = tail.next) {
            if (i == index) {
                return tail;
            }
        }
        return null;
    }

    /**
     * 下标插入
     */
    public void add(int index, T value) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("越界");
        }
        if(size == 0){
            header = new Node<>(value,null,null);
            header.next = header;
            header.prev = header;
        }else if (index == 0){
            Node<T> node = header;
            Node<T> prev = header.prev;
            header = new Node<>(value,node,prev);
            node.prev = header;
            prev.next = header;
        }else{
            Node<T> prev = getIndex(index - 1);
            Node<T> next = prev.next;
            Node<T> node = new Node<>(value,next,prev);
            next.prev = node;
            prev.next = node;
        }
        size++;
    }

    public T delete(int index){
        if(isEmpty()){ throw new IndexOutOfBoundsException("链表为空");}
        if (index > size-1 || index < 0) { throw new IndexOutOfBoundsException("越界"); }
        Node<T> node = null;
        if(index == 0){
            node = header;
            Node<T> prev = node.prev;
            Node<T> next = node.next;
            header = next;
            prev.next = header;
            header.prev = next;
        } else {
            node = getIndex(index);
            Node<T> prev = node.prev;
            Node<T> next = node.next;
            prev.next = next;
            next.prev = prev;
        }
        size --;
        return node.data;
    }

    public String toString(){
        if(isEmpty()){
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        int i = 0;
        for(Node<T> node = header;i < size ; node = node.next , i ++){
            sb.append(node.data+",\t");
        }
        return sb.append("]").toString();
    }

    public static void main(String[] args){
        XunHuanNode<Integer> node = new XunHuanNode<>();
        for(int i = 0 ; i < 10 ; i ++){
            node.add(i,i);
        }
        node.add(1,11);
        node.delete(0);
        System.out.println(node.toString());
    }

}
