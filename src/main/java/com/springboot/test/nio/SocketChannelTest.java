package com.springboot.test.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * SocketChannelTest
 *
 * @author txw
 * @date 2021/7/1 10:03
 */
public class SocketChannelTest {

	public static void main(String[] args) throws Exception {

		// 等价于
//		SocketChannel socketChannel = SocketChannel.open();
//		socketChannel.connect(new InetSocketAddress("127.0.0.1",8080));
		SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8080));

		ByteBuffer byteBuffer = ByteBuffer.wrap("测试nio发送消息".getBytes());
		socketChannel.write(byteBuffer);
		int num;
		while ((num = socketChannel.read(byteBuffer)) > 0) {
			byteBuffer.flip();
			byte[] re = new byte[num];
			byteBuffer.get(re);

			String result = new String(re, "UTF-8");
			System.out.println("返回值: " + result);
		}
	}
}
