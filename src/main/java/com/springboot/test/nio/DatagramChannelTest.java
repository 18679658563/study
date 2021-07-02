package com.springboot.test.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * DatagramChannelTest  UDP 是面向无连接的，不需要和对方握手，不需要通知对方，就可以直接将数据包投出去，至于能不能送达，它是不知道的
 *
 * @author txw
 * @date 2021/7/1 10:53
 */
public class DatagramChannelTest {

	public static void main(String[] args) throws Exception {
		DatagramChannel channel = DatagramChannel.open();
		channel.socket().bind(new InetSocketAddress(9090));
		ByteBuffer buf = ByteBuffer.allocate(48);

		channel.receive(buf);
		String newData = "New String to write to file..." + System.currentTimeMillis();

		buf.put(newData.getBytes());
		buf.flip();

		int bytesSent = channel.send(buf, new InetSocketAddress("jenkov.com", 80));
	}
}
