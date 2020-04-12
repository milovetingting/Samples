package com.wangyz.netty.client.connector;

import com.wangyz.netty.client.Global;
import com.wangyz.netty.client.bean.Msg;
import com.wangyz.netty.client.connection.IMsgCallback;
import com.wangyz.netty.client.util.LogUtil;
import com.wangyz.netty.client.util.MsgUtil;
import com.wangyz.netty.client.wrapper.ChannelHandlerContextWrapper;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

public class NettyClientHandler extends ChannelInboundHandlerAdapter {

	private ChannelHandlerContextWrapper mChannelHandlerContextWrapper;

	private IMsgCallback msgCallback;

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		LogUtil.log("Client,channelActive");
		mChannelHandlerContextWrapper = new ChannelHandlerContextWrapper(ctx);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		LogUtil.log("Client,接收到服务端发来的消息:" + msg);
		if (msgCallback != null) {
			Msg responseMsg = Global.sGson.fromJson((String) msg, Msg.class);
			msgCallback.onMsgReceived(responseMsg);
		}
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
			MsgUtil.sendHeartBeatMsg(mChannelHandlerContextWrapper);
			break;
		case ALL_IDLE:
			break;
		default:
			super.userEventTriggered(ctx, evt);
			break;
		}
	}

	public void sendMsg(Msg msg) {
		MsgUtil.sendMsg(mChannelHandlerContextWrapper, msg);
	}

	public void registerMsgCallback(IMsgCallback callback) {
		if (callback != null) {
			msgCallback = callback;
		}
	}

}
