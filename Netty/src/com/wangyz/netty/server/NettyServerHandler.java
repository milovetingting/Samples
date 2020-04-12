package com.wangyz.netty.server;

import com.wangyz.netty.server.bean.Msg;
import com.wangyz.netty.server.command.CommandFactory;
import com.wangyz.netty.server.dispatcher.MsgDispatcher;
import com.wangyz.netty.server.util.LogUtil;
import com.wangyz.netty.server.util.MsgUtil;
import com.wangyz.netty.server.wrapper.ChannelHandlerContextWrapper;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {

	private ChannelHandlerContextWrapper mChannelHandlerContextWrapper;

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		LogUtil.log("Server,channelActive");
		mChannelHandlerContextWrapper = new ChannelHandlerContextWrapper(ctx);
		MsgUtil.sendWelcomeMsg(mChannelHandlerContextWrapper);
		CommandFactory.pushCommand(mChannelHandlerContextWrapper);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		LogUtil.log("Server,接收到客户端发来的消息:" + msg);
		Msg requestMsg = Global.sGson.fromJson((String) msg, Msg.class);
		MsgDispatcher.dispatch(mChannelHandlerContextWrapper, requestMsg);
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
