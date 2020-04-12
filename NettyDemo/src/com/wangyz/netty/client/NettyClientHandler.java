package com.wangyz.netty.client;

import com.wangyz.netty.client.util.LogUtil;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

public class NettyClientHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		LogUtil.log("Client,channelActive");
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		LogUtil.log("Client,接收到服务端发来的消息:" + msg);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		LogUtil.log("Client,exceptionCaught");
		cause.printStackTrace();
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		LogUtil.log("Client,channelInactive");
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		IdleStateEvent event = (IdleStateEvent) evt;
		LogUtil.log("Client,Idle:" + event.state());
		switch (event.state()) {
		case READER_IDLE:

			break;
		case WRITER_IDLE:
			ctx.writeAndFlush("心跳^v^" + Config.DATA_PACK_SEPARATOR);
			break;
		case ALL_IDLE:
			break;
		default:
			super.userEventTriggered(ctx, evt);
			break;
		}
	}

}
