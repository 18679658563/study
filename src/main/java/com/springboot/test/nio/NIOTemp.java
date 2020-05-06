package com.springboot.test.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * Nio原理  利用单线程解决并发，解决bio的阻塞问题
 */
public class NIOTemp  {

    static ByteBuffer bb = ByteBuffer.allocate(1024);
    //保存nio连接客户端 ,保证历史通道读取
    static List<SocketChannel> channelList = new ArrayList<>();
    public static void main(String[] args) {
        try {
            //创建 nio  socket通道
            ServerSocketChannel ss = ServerSocketChannel.open();
            //设置地址
            SocketAddress sa = new InetSocketAddress("127.0.0.1",9090);
            ss.bind(sa);
            //设置成非阻塞
            ss.configureBlocking(false);
            while(true){
                //导致循环一些没有新消息的socket连接
                for(SocketChannel sc : channelList){
                    int read = sc.read(bb);
                    //read 》 0 表示有人想当前通道发送信息,读取信息
                    if(read > 0){
                        System.out.println(read);
                        bb.flip();
                        byte[] b = new byte[read];
                        bb.get(b);
                        String content = new String(b);
                        System.out.println(content);
                        bb.flip();
                    } else if(read == -1){
                        //表示断开连接
                        channelList.remove(sc);
                    }
                }
                //判断是否有点连接
                SocketChannel sc = ss.accept();
                if(sc != null){
                    System.out.println("connection success");
                    //设置成非阻塞
                    sc.configureBlocking(false);
                    channelList.add(sc);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
