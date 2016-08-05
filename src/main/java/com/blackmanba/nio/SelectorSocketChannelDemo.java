package com.blackmanba.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class SelectorSocketChannelDemo {

		public static void startClient() throws Exception{
			SocketChannel socketChannel=SocketChannel.open();
			socketChannel.connect(new InetSocketAddress("localhost",8999));
			socketChannel.configureBlocking(false);
			
			Selector selector = Selector.open();
			socketChannel.register(selector, SelectionKey.OP_READ);
			new ClientThread(selector).start();
			
			ByteBuffer byteBuffer=ByteBuffer.wrap("hello 我是客户端".getBytes("UTF-8"));
			socketChannel.write(byteBuffer);
			byteBuffer.clear();
			
			
		}
		public static void main(String[] args) throws Exception {
			startClient();
		}
}
