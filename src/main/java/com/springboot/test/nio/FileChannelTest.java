package com.springboot.test.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * FileChannelTest
 *
 * @author txw
 * @date 2021/7/1 9:38
 */
public class FileChannelTest {

	public static void main(String[] args) throws Exception{
		FileInputStream fileInputStream = new FileInputStream("C:\\Users\\silence\\Desktop\\note.txt");
		FileChannel fileChannel = fileInputStream.getChannel();
		ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

		FileChannel out = new FileOutputStream("C:\\Users\\silence\\Desktop\\note-out.txt").getChannel();
		while(fileChannel.read(byteBuffer) > 0) {
			byteBuffer.flip();
			out.write(byteBuffer);
			byteBuffer.clear();
		}
		fileChannel.close();
		out.close();
	}

}
