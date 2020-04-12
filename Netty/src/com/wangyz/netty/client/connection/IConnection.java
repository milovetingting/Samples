package com.wangyz.netty.client.connection;

import com.wangyz.netty.client.bean.Msg;

public interface IConnection {

	/**
	 * 连接服务器
	 * 
	 * @param host     服务器地址
	 * @param port     端口
	 * @param callback 连接回调
	 */
	public void connect(String host, int port, IConnectionCallback callback);

	/**
	 * 发送消息
	 * 
	 * @param msg
	 */
	public void sendMsg(Msg msg);

	/**
	 * 注册消息回调
	 * 
	 * @param callback
	 */
	public void registerMsgCallback(IMsgCallback callback);

}
