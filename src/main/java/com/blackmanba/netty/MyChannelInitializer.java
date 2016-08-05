package com.blackmanba.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;


public class MyChannelInitializer extends ChannelInitializer<NioSocketChannel>{



	@Override
	protected void initChannel(NioSocketChannel ch) throws Exception {
		ch.pipeline().addLast(new DicardServerHandler());
		
	}

}
