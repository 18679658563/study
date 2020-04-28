package com.springboot.test.jvm;

/***
 * Created with IntelliJ IDEA.
 * Description: 通过不断建立线程的方式产生内存溢出异常
 *              args： -Xss2M
 * User: silence
 * Date: 2019-04-01
 * Time: 上午9:29
 */
public class JVMStackOOM {

        private void dontStop(){
            while(true){}
        }

        public void stackLeakByThread(){
            while(true){
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        dontStop();
                    }
                });
                thread.start();
            }
        }

        public static void main(String[] args){
            JVMStackOOM oom = new JVMStackOOM();
            oom.stackLeakByThread();
        }

}
