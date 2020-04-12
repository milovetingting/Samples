package com.wangyz.netty.client.connection;

import com.wangyz.netty.client.bean.Msg;

public interface IMsgCallback {

	/**
	 * 接收到消息时的回调
	 * 
	 * @param msg
	 */
	public void onMsgReceived(Msg msg);

}
