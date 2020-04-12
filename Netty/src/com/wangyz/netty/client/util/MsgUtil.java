package com.wangyz.netty.client.util;

import com.wangyz.netty.client.Global;
import com.wangyz.netty.client.bean.Msg;
import com.wangyz.netty.client.wrapper.ChannelHandlerContextWrapper;

public class MsgUtil {

	/**
	 * 发送消息
	 * 
	 * @param wrapper
	 * @param msg
	 */
	public static void sendMsg(ChannelHandlerContextWrapper wrapper, Msg msg) {
		wrapper.writeAndFlush(Global.sGson.toJson(msg));
	}

	/**
	 * 发送欢迎消息
	 * 
	 * @param wrapper
	 */
	public static void sendWelcomeMsg(ChannelHandlerContextWrapper wrapper) {
		Msg msg = new Msg();
		msg.type = Msg.TYPE_WELCOME;
		msg.msg = "你好，客户端";
		wrapper.writeAndFlush(Global.sGson.toJson(msg));
	}

	/**
	 * 发送心跳消息
	 * 
	 * @param wrapper
	 */
	public static void sendHeartBeatMsg(ChannelHandlerContextWrapper wrapper) {
		Msg msg = new Msg();
		msg.type = Msg.TYPE_HEART_BEAT;
		wrapper.writeAndFlush(Global.sGson.toJson(msg));
	}

}
