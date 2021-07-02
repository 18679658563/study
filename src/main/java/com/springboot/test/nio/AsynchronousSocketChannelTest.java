package com.springboot.test.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.util.concurrent.Future;

/**
 * AsynchronousSocketChannelTest 非阻塞 IO  异步id
 *
 * @author txw
 * @date 2021/7/1 17:03
 */
public class AsynchronousSocketChannelTest {

	public static void main(String[] args) throws Exception {
		AsynchronousSocketChannel client = AsynchronousSocketChannel.open();

		Future<?> future = client.connect(new InetSocketAddress(8080));
		// 阻塞一下，等待连接成功
		future.get();
		AttachmentTest att = new AttachmentTest();
		att.setClient(client);
		att.setReadMode(false);
		att.setBuffer(ByteBuffer.allocate(2048));
		byte[] data = "I am obot!".getBytes();
		att.getBuffer().put(data);
		att.getBuffer().flip();

		// 异步发送数据到服务端
		client.write(att.getBuffer(), att, new CompletionHandler<Integer, AttachmentTest>() {
			@Override
			public void completed(Integer result, AttachmentTest attachment) {
				ByteBuffer buffer = attachment.getBuffer();
				if (att.isReadMode()) {
					// 读取服务端的数据
					buffer.flip();
					byte[] bytes = new byte[buffer.limit()];
					buffer.get(bytes);
					String msg = new String(bytes, Charset.forName("UTF-8"));
					System.out.println("收到来自服务端的响应数据: " + msg);
					// 接下来，有以下两种选择:
					// 1. 向服务端发送新的数据
//		            att.setReadMode(false);
//		            buffer.clear();
//		            String newMsg = "new message from client";
//		            byte[] data = newMsg.getBytes(Charset.forName("UTF-8"));
//		            buffer.put(data);
//		            buffer.flip();
//		            att.getClient().write(buffer, att, this);
					// 2. 关闭连接
					try {
						att.getClient().close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					// 写操作完成后，会进到这里
					att.setReadMode(true);
					buffer.clear();
					att.getClient().read(buffer, att, this);
				}
			}

			@Override
			public void failed(Throwable exc, AttachmentTest attachment) {
				System.out.println("服务器无响应");
			}
		});
		// 这里休息一下再退出，给出足够的时间处理数据
		Thread.sleep(2000);
	}

}
