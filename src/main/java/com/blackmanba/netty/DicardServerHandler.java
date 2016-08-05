package com.blackmanba.netty;

import java.nio.ByteBuffer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class DicardServerHandler extends ChannelHandlerAdapter {

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		ctx.close();

	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		ByteBuf buf = (ByteBuf) msg;
		int s = 0;
		while (buf.isReadable()) {
			s=buf.readInt();
			System.out.print(s);
			//System.out.flush();
		}
		ByteBuf wbuf=ctx.alloc().buffer().writeBytes(("i hava recieved"+s).getBytes("UTF-8"));
		ctx.writeAndFlush(wbuf);
		
	}
}
