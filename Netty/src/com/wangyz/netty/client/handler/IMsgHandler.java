package com.wangyz.netty.client.handler;

import com.wangyz.netty.client.bean.Msg;

public interface IMsgHandler {

	/**
	 * 处理消息
	 * 
	 * @param msg
	 */
	public void handle(Msg msg);

}
