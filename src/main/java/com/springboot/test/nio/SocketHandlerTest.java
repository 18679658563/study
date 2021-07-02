package com.springboot.test.nio;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * SocketHandlerTest
 *
 * @author txw
 * @date 2021/7/1 15:04
 */
public class SocketHandlerTest implements Runnable {

	private SocketChannel socketChannel;

	public SocketHandlerTest(SocketChannel socketChannel) {
		this.socketChannel = socketChannel;
	}

	@Override
	public void run() {
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		try {
			// 将请求数据读入 Buffer 中
			int num;
			while ((num = socketChannel.read(buffer)) > 0) {
				// 读取 Buffer 内容之前先 flip 一下
				buffer.flip();

				// 提取 Buffer 中的数据
				byte[] bytes = new byte[num];
				buffer.get(bytes);

				String re = new String(bytes, "UTF-8");
				System.out.println("收到请求：" + re);

				// 回应客户端
				ByteBuffer writeBuffer = ByteBuffer.wrap(("请求内容是：" + re).getBytes());
				socketChannel.write(writeBuffer);

				buffer.clear();
			}
		} catch (IOException e) {
			IOUtils.closeQuietly(socketChannel);
		}
	}
}
