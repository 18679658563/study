package com.springboot.test.data_structure;

/***
 * Created with IntelliJ IDEA.
 * Description: 栈是一种先进后出的数据结构，我们把允许插入和删除的一端称为栈顶，另一端称为栈底，不含任何元素的栈称为空栈
 * User: silence
 * Date: 2019-08-19
 * Time: 下午2:21
 */
public class ArrayStack<T> {

    private T data[];

    private int mixSize;

    private int top;

    /**
     *     初始化栈　
     */
    public ArrayStack(int size){
        this.mixSize = size;
        this.data = (T[])new Object[mixSize];
        this.top = -1;
    }

    /**
     * 判断栈是否为空
     * @return
     */
    public boolean isEmpty(){
        return top == -1;
    }

    /**
     * 判断栈是否满了
     * @return
     */
    public boolean isFull(){
        return (mixSize - 1) == top;
    }

    /**
     * 入栈
     * @return
     */
    public boolean push(T t){
        if(isFull()){
            return false;
        }
        top ++ ;
        data[top] = t;
        return true;
    }

    /**
     * 出栈
     * @return
     */
    public T pop(){
        if(isEmpty()){
            return null;
        }
        T value = data[top];
        data[top] = null;
        top --;
        return value;
    }



    public static void main(String[] args){
        ArrayStack<Integer> stack = new ArrayStack<>(5);
        for (int i = 0 ; i < 6 ; i++){
            stack.push(i);
        }
        for (int i = 0 ; i < 6 ; i++){
            System.out.println(stack.pop());
        }
    }
}
