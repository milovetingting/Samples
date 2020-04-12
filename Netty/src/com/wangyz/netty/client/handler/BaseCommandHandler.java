package com.wangyz.netty.client.handler;

import com.wangyz.netty.client.bean.Msg;
import com.wangyz.netty.client.dispatcher.MsgDispatcher;
import com.wangyz.netty.client.dispatcher.MsgQueue;
import com.wangyz.netty.client.util.LogUtil;

public abstract class BaseCommandHandler implements IMsgHandler {

	@Override
	public void handle(Msg msg) {
		execute(msg);
	}

	public final void execute(Msg msg) {
		LogUtil.log("Client,received command:" + msg);
		doHandle(msg);
		MsgQueue.getInstance().makeUse(false);
		LogUtil.log("Client,report command:" + msg);
		MsgDispatcher.dispatch();
	}

	public abstract void doHandle(Msg msg);

}
