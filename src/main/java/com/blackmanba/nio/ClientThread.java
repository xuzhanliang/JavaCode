package com.blackmanba.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class ClientThread extends Thread{
	private Selector selector;
	
	public ClientThread(Selector selector) {
		super();
		this.selector = selector;
	}
/**
 * 首先，我们调用 Selector 的 select() 方法。这个方法会阻塞，直到至少有一个已注册的事件发生。当一个或者更多的事件发生时， select() 方法将返回所发生的事件的数量。
接下来，我们调用 Selector 的 selectedKeys() 方法，它返回发生了事件的 SelectionKey 对象的一个 集合 。
我们通过迭代 SelectionKeys 并依次处理每个 SelectionKey 来处理事件。对于每一个 SelectionKey，您必须确定发生的是什么 I/O 事件，以及这个事件影响哪些 I/O 对象。
 */
	@Override
	public void run() {
		try {
			while(selector.select()>0){
				for(SelectionKey key :selector.keys()){
					SocketChannel socketChannel=(SocketChannel)key.channel();
					ByteBuffer buf = ByteBuffer.allocate(40);
					int size = socketChannel.read(buf);
					while(size>0){
						buf.flip();
						Charset charset = Charset.forName("UTF-8");
						System.out.println(charset.newDecoder().decode(buf).toString());
						size = socketChannel.read(buf);
					}
					//调用 remove() 方法来删除处理过的 SelectionKey：
					selector.selectedKeys().remove(key);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
