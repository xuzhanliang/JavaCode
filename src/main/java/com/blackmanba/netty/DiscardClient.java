package com.blackmanba.netty;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import com.blackmanba.netty.constents.CommonConstant;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;

public class DiscardClient {
	public static Bootstrap b;
	public static PooledByteBufAllocator allocator = new PooledByteBufAllocator();
	static{
		try {
			EventLoopGroup workGroup = new NioEventLoopGroup();

				b = new Bootstrap();
				b.group(workGroup);
				b.channel(NioSocketChannel.class);
				b.option(ChannelOption.SO_KEEPALIVE, true);
				b.handler(new ChannelInitializer<NioSocketChannel>() {

					@Override
					protected void initChannel(NioSocketChannel ch)
							throws Exception {
						ch.pipeline().addLast(new DiscardClientHandler());
					}
				});
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
	
	public static Object startClient(int obj) throws Exception {
		ChannelFuture f = b.connect("localhost", 8999).sync();
//		f.channel().attr(AttributeKey.valueOf(CommonConstant.ATTRIBUTE_KEY))
//				.set(obj);
		
		ByteBuf buf = allocator.buffer().writeInt(obj);
		f.channel().writeAndFlush(buf);
		
		f.channel().closeFuture().sync();
		
		return f.channel()
				.attr(AttributeKey.valueOf(CommonConstant.ATTRIBUTE_KEY)).get();
//		 finally {
//			workGroup.shutdownGracefully();
//		}
		

	}
	public static void main(String[] args) {
		
	}
}