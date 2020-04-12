package com.wangyz.netty;

import com.google.gson.Gson;
import com.wangyz.netty.client.bean.Msg;
import com.wangyz.netty.client.bean.Msg.LoginRuquestInfo;
import com.wangyz.netty.client.connection.ConnectionManager;
import com.wangyz.netty.client.connection.IConnectionCallback;
import com.wangyz.netty.client.connection.IMsgCallback;
import com.wangyz.netty.client.connection.NettyConnection;
import com.wangyz.netty.client.dispatcher.MsgDispatcher;
import com.wangyz.netty.client.dispatcher.MsgQueue;
import com.wangyz.netty.client.util.LogUtil;
import com.wangyz.netty.server.NettyServer;

public class Main {

	public static void main(String[] args) {
		try {
			String host = "127.0.0.1";
			int port = 12345;
			NettyServer server = new NettyServer(port);
			server.run();
			Thread.sleep(1000);
			ConnectionManager.initConnection(new NettyConnection());
			ConnectionManager.getInstance().connect(host, port, new IConnectionCallback() {

				@Override
				public void onConnected() {
					LogUtil.log("Main,onConnected");

					ConnectionManager.getInstance().registerMsgCallback(new IMsgCallback() {

						@Override
						public void onMsgReceived(Msg msg) {
							MsgQueue.getInstance().enqueueMsg(msg);
							MsgDispatcher.dispatch();
						}
					});

					Msg msg = new Msg();
					msg.type = Msg.TYPE_LOGIN;

					Msg.LoginRuquestInfo request = new LoginRuquestInfo();
					request.user = "wangyz";
					request.pwd = "wangyz";

					Gson gson = new Gson();
					msg.msg = gson.toJson(request);

					ConnectionManager.getInstance().sendMsg(msg);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
