package com.springboot.test.jvm;

/***
 * Created with IntelliJ IDEA.
 * Description: 虚拟机栈和本地方法栈oom测试
 *              结果表明：在单线程下，无论是栈帧太大还是虚拟机栈容量太小，当内存无法分配的时候，都抛出StackOverflowError异常
 * User: silence
 * Date: 2019-04-01
 * Time: 上午9:17
 */
public class JVMStackSOF {

    private int stackLength = 1;

    public void stackLeak(){
        stackLength ++;
        stackLeak();
    }

    public static void main(String[] args) throws Throwable{
        JVMStackSOF oom = new JVMStackSOF();
        try{
            oom.stackLeak();
        }catch(Throwable r){
            System.out.println("stack length : " + oom.stackLength);
            throw r;
        }
    }

}
