package com.wangyz.netty.server.dispatcher;

import com.wangyz.netty.server.bean.Msg;
import com.wangyz.netty.server.wrapper.ChannelHandlerContextWrapper;

public interface IMsgHandler {

	/**
	 * 处理消息
	 * 
	 * @param mChannelHandlerContextWrapper
	 * 
	 * @param msg
	 */
	public void handle(ChannelHandlerContextWrapper mChannelHandlerContextWrapper, Msg msg);

}
