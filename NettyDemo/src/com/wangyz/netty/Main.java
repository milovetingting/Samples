package com.wangyz.netty;

import com.wangyz.netty.client.NettyClient;
import com.wangyz.netty.server.NettyServer;

public class Main {

	public static void main(String[] args) {
		try {
			String host = "127.0.0.1";
			int port = 12345;
			NettyServer server = new NettyServer(port);
			server.run();
			Thread.sleep(1000);
			NettyClient client = new NettyClient(host, port);
			client.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
