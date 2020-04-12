package com.wangyz.netty.server;

import com.wangyz.netty.server.util.LogUtil;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		LogUtil.log("Server,channelActive");
		ctx.writeAndFlush("你好，客户端" + Config.DATA_PACK_SEPARATOR);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		LogUtil.log("Server,接收到客户端发来的消息:" + msg);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		LogUtil.log("Server,exceptionCaught");
		cause.printStackTrace();
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		LogUtil.log("Server,channelInactive");
	}

}
