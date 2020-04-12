package com.wangyz.netty.client.connection;

import com.wangyz.netty.client.bean.Msg;
import com.wangyz.netty.client.connector.NettyClient;

public class NettyConnection implements IConnection {

	private NettyClient mClient;

	@Override
	public void connect(String host, int port, IConnectionCallback callback) {
		if (mClient == null) {
			mClient = new NettyClient(host, port);
			mClient.setConnectionCallBack(callback);
			mClient.connect();
		}
	}

	@Override
	public void sendMsg(Msg msg) {
		if (mClient == null) {
			throw new IllegalAccessError("please invoke connect first!");
		}
		mClient.sendMsg(msg);
	}

	@Override
	public void registerMsgCallback(IMsgCallback callback) {
		if (mClient == null) {
			throw new IllegalAccessError("please invoke connect first!");
		}
		mClient.registerMsgCallback(callback);
	}

}
