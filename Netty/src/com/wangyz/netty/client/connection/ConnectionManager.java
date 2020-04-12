package com.wangyz.netty.client.connection;

import com.wangyz.netty.client.bean.Msg;

public class ConnectionManager implements IConnection {

	private static IConnection mConnection;

	private ConnectionManager() {

	}

	static class ConnectionManagerInner {
		private static ConnectionManager INSTANCE = new ConnectionManager();
	}

	public static ConnectionManager getInstance() {
		return ConnectionManagerInner.INSTANCE;
	}

	public static void initConnection(IConnection connection) {
		mConnection = connection;
	}

	private void checkInit() {
		if (mConnection == null) {
			throw new IllegalAccessError("please invoke initConnection first!");
		}
	}

	@Override
	public void connect(String host, int port, IConnectionCallback callback) {
		checkInit();
		mConnection.connect(host, port, callback);
	}

	@Override
	public void sendMsg(Msg msg) {
		checkInit();
		mConnection.sendMsg(msg);
	}

	@Override
	public void registerMsgCallback(IMsgCallback callback) {
		checkInit();
		mConnection.registerMsgCallback(callback);
	}

}
