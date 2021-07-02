package com.springboot.test.nio;

import lombok.Data;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;

/**
 * AsynchronousServerSocketChannelTest 非阻塞 IO 的 ServerSocketChannel
 *
 * @author txw
 * @date 2021/7/1 16:54
 */
public class AsynchronousServerSocketChannelTest {

	public static void main(String[] args) throws Exception {
		AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(8080));

		// AttachmentTest 类，用于传递一些信息
		AttachmentTest att = new AttachmentTest();
		att.setServer(server);

		server.accept(att, new CompletionHandler<AsynchronousSocketChannel, AttachmentTest>() {
			@Override
			public void completed(AsynchronousSocketChannel result, AttachmentTest attachment) {
				try {
					SocketAddress clientAddr = result.getRemoteAddress();
					System.out.println("收到新的连接：" + clientAddr);

					// 收到新的连接后，server 应该重新调用 accept 方法等待新的连接进来
					att.getServer().accept(att, this);

					AttachmentTest newAtt = new AttachmentTest();
					newAtt.setServer(server);
					newAtt.setClient(result);
					newAtt.setReadMode(true);
					newAtt.setBuffer(ByteBuffer.allocate(2048));

					// 这里也可以继续使用匿名实现类
					result.read(newAtt.getBuffer(), newAtt, new CompletionHandler() {
						@Override
						public void completed(Object result, Object attachment) {
							if (att.isReadMode()) {
								// 读取来自客户端的数据
								ByteBuffer buffer = att.getBuffer();
								buffer.flip();
								byte bytes[] = new byte[buffer.limit()];
								buffer.get(bytes);
								String msg = new String(buffer.array()).trim();
								System.out.println("收到来自客户端的数据: " + msg);

								// 响应客户端请求，返回数据
								buffer.clear();
								buffer.put("Response from server!".getBytes(Charset.forName("UTF-8")));
								att.setReadMode(false);
								buffer.flip();
								// 写数据到客户端也是异步
								att.getClient().write(buffer, att, this);
							} else {
								// 到这里，说明往客户端写数据也结束了，有以下两种选择:
								// 1. 继续等待客户端发送新的数据过来
//           					 att.setReadMode(true);
//           					 att.getBuffer().clear();
//            					 att.getClient().read(att.getBuffer(), att, this);
								// 2. 既然服务端已经返回数据给客户端，断开这次的连接
								try {
									att.getClient().close();
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						}

						@Override
						public void failed(Throwable exc, Object attachment) {
							System.out.println("连接断开");
						}
					});

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			@Override
			public void failed(Throwable exc, AttachmentTest attachment) {
				System.out.println("accept failed");
			}
		});
		// 为了防止 main 线程退出
		try {
			Thread.currentThread().join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

@Data
class AttachmentTest {
	private AsynchronousServerSocketChannel server;
	private AsynchronousSocketChannel client;
	private boolean isReadMode;
	private ByteBuffer buffer;
}
