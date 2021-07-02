package com.springboot.test.nio;

import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * SocketServerChannelTest
 *
 * @author txw
 * @date 2021/7/1 10:08
 */
public class SocketServerChannelTest {

	public static void main(String[] args) throws Exception {

		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.socket().bind(new InetSocketAddress(8080));
		while (true) {
			System.out.println("监听开始----");
			SocketChannel accept = serverSocketChannel.accept();
			SocketHandlerTest handler = new SocketHandlerTest(accept);
			new Thread(handler).start();
			System.out.println("监听到----");
		}
	}

}
