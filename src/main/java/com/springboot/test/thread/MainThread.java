package com.springboot.test.thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * Created with IntelliJ IDEA.
 * Description: 一个正常普通的java程序中包含的线程
 * User: silence
 * Date: 2018-12-04
 * Time: 上午9:09
 */
public class MainThread {

    public static void main(String[] args){
        //获取java线程管理MXBean
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        //不需要获取同步的monitor和synchronizer信息,仅获取线程和线程堆栈信息
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false,false);
        //打印出所有线程
        for(ThreadInfo info : threadInfos){
            System.out.println("线程id：" + info.getThreadId() + ",线程名字：" + info.getThreadName());
            /*
            1.main //main线程，程序的主入口
            2.Reference Handler //消除Reference的线程
            3.Finalizer //调用对象finalizer的线程
            4.Signal Dispatcher //分发处理发送给jvm信号的线程
            //Debug启动：5.JDWP Transport Listener: dt_socket //该线程是一个Java Debugger的监听器线程，负责受理客户端的debug请求。通常我们习惯将它的监听端口设置为8787
            //            6.JDWP Event Helper Thread //JDWP是通讯交互协议，它定义了调试器和被调试程序之间传递信息的格式。它详细完整地定义了请求命令、回应数据和错误代码，保证了前端和后端的JVMTI和JDI的通信通畅。 该线程主要负责将JDI事件映射成JVMTI信号，以达到调试过程中操作JVM的目的。
            //
             正常启动：5.Monitor Ctrl-break：
             */

        }

    }
}
