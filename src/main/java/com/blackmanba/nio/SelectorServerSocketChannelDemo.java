package com.blackmanba.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class SelectorServerSocketChannelDemo {
	public static void main(String[] args) throws Exception {
		startServer();
	}

	public static void startServer() throws Exception {
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.socket().bind(new InetSocketAddress(8999));
		serverSocketChannel.configureBlocking(false);// 非阻塞

		Selector selector = Selector.open();
		//参数 OP_ACCEPT，它指定我们想要监听 accept 事件，也就是在新的连接建立时所发生的事件。
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		while (true) {

			// 每一个连接只会select一次,当一个或者更多的事件发生时， select() 方法将返回所发生的事件的数量。
			int select = selector.select();
			if (select > 0) {
				for (SelectionKey key : selector.selectedKeys()) {
					if (key.isAcceptable()) {
						SocketChannel socketChannel = ((ServerSocketChannel) key
								.channel()).accept();
						ByteBuffer buf = ByteBuffer.allocate(40);
						int size = socketChannel.read(buf);
						while (size > 0) {
							buf.flip();
							Charset charset = Charset.forName("UTF-8");
							System.out.print(charset.newDecoder().decode(buf)
									.toString());
							size = socketChannel.read(buf);
						}
						buf.clear();

						ByteBuffer response = ByteBuffer.wrap("我已经收到你的请求"
								.getBytes("UTF-8"));
						socketChannel.write(response);
						socketChannel.close();
						selector.selectedKeys().remove(key);

					}
				}
			}
		}
	}
}
