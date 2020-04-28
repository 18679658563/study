package com.springboot.test.jvm;

/***
 * Created with IntelliJ IDEA.
 * Description: 对象可以在GC时自我拯救
 *              自救机会只有一次，因为一个对象的finalize()方法最多只会被系统自动调用一次
 * User: silence
 * Date: 2019-04-02
 * Time: 上午9:34
 */
public class FinalizeEscapeGC {

    public static FinalizeEscapeGC SAVE_HOOK = null;

    public void isAlive(){
        System.out.println("is alive");
    }

    @Override
    protected void finalize() throws Throwable{
        super.finalize();
        System.out.println("finalize method  executed");
        FinalizeEscapeGC.SAVE_HOOK = this;
    }

    public static void main(String[] args) throws Throwable{
        SAVE_HOOK = new FinalizeEscapeGC();
        //对象第一次成功拯救自己
        SAVE_HOOK = null;
        System.gc();
        //因为finalize方法优先级很低，所以暂停1秒等待它
        Thread.sleep(1000);
        if(SAVE_HOOK != null){
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("is dead");
        }
        //自救失败，因为finalize方法只会被系统调用一次
        SAVE_HOOK = null;
        System.gc();
        //因为finalize方法优先级很低，所以暂停1秒等待它
        Thread.sleep(1000);
        if(SAVE_HOOK != null){
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("is dead");
        }
    }

}
