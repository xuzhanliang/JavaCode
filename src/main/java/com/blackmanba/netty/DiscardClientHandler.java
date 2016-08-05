package com.blackmanba.netty;

import java.net.SocketAddress;

import com.blackmanba.netty.constents.CommonConstant;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.cors.CorsConfig.Builder;
import io.netty.util.AttributeKey;

public class DiscardClientHandler extends ChannelHandlerAdapter {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
//		int req=(Integer) ctx.channel().attr(AttributeKey.valueOf(CommonConstant.ATTRIBUTE_KEY)).get();
//		System.out.println("请求数据为{}"+req);
//		ByteBuf buf = ctx.alloc().buffer().writeInt(req);
//		ctx.writeAndFlush(buf);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		ByteBuf buf = (ByteBuf) msg;
		StringBuilder builder = new StringBuilder();
		while (buf.isReadable()) {
			builder.append((char) buf.readByte());

		}
		ctx.channel().attr(AttributeKey.valueOf(CommonConstant.ATTRIBUTE_KEY))
				.set(builder.toString());
		ctx.channel().close();
		ctx.close();
	}
}
