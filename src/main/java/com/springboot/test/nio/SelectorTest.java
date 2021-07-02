package com.springboot.test.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * SelectorTest
 *
 * @author txw
 * @date 2021/7/1 14:17
 */
public class SelectorTest {

	public static void main(String[] args) throws Exception {
		ServerSocketChannel server = ServerSocketChannel.open();
		server.socket().bind(new InetSocketAddress(8080));
		// 设置通道为非阻塞模式
		server.configureBlocking(false);

		Selector selector = Selector.open();
		// 将channel注册到selector上  ，监听 OP_ACCEPT 事件
		server.register(selector, SelectionKey.OP_ACCEPT);

		while (true) {
			// 是否有准备好的时间
			int readyChannels = selector.select();
			if (readyChannels == 0) {
				continue;
			}
			// 遍历
			Set<SelectionKey> selectionKeys = selector.selectedKeys();
			Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
			while (keyIterator.hasNext()) {
				SelectionKey next = keyIterator.next();
				keyIterator.remove();

				if (next.isAcceptable()) {
					// 有已经接受的新的到服务端的连接
					SocketChannel socketChannel = server.accept();

					// 有新的连接并不代表这个通道就有数据，
					// 这里将这个新的 SocketChannel 注册到 Selector，监听 OP_READ 事件，等待数据
					socketChannel.configureBlocking(false);
					socketChannel.register(selector, SelectionKey.OP_READ);

				} else if (next.isConnectable()) {
					// a connection was established with a remote server.

				} else if (next.isReadable()) {
					// 有数据可读
					// 上面一个 if 分支中注册了监听 OP_READ 事件的 SocketChannel
					SocketChannel socketChannel = (SocketChannel) next.channel();
					ByteBuffer readBuffer = ByteBuffer.allocate(1024);
					int num = socketChannel.read(readBuffer);
					if (num > 0) {
						// 处理进来的数据...
						System.out.println("收到数据：" + new String(readBuffer.array()).trim());
						ByteBuffer buffer = ByteBuffer.wrap("返回给客户端的数据...".getBytes());
						socketChannel.write(buffer);
					} else if (num == -1) {
						// -1 代表连接已经关闭
						socketChannel.close();
					}
				} else if (next.isWritable()) {
					// a channel is ready for writing
				}
			}
		}

	}
}
