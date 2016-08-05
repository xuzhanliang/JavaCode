package com.blackmanba.nio;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class ByteBufferDemo {

	//主要通过读取文件内容，写到ByteBuffer里，然后从ByteBuffer对象中获取数据，显示到控制台
	public static void readFile(String fileName) throws Exception{
		RandomAccessFile randomAccessFile = new RandomAccessFile(fileName, "rw");
		FileChannel fileChannel = randomAccessFile.getChannel();//创建channel
		
		ByteBuffer byteBuffer = ByteBuffer.allocate(48);//分配48个字节给ByteBuffer
		int size = fileChannel.read(byteBuffer);//把bytebuffer读到channel中
		while(size>0){
			byteBuffer.flip();//从写模式转换为读取模式
			while(byteBuffer.remaining()>0){
			System.out.println(byteBuffer.get());
			}
//			Charset charset = Charset.forName("UTF-8");
//			System.out.print(charset.newDecoder().decode(byteBuffer).toString());
			
			byteBuffer.clear();
			
			size = fileChannel.read(byteBuffer);
		}
		fileChannel.close();
		randomAccessFile.close();
	}
}
