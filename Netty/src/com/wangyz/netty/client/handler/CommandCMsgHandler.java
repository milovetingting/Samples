package com.wangyz.netty.client.handler;

import com.wangyz.netty.client.bean.Msg;
import com.wangyz.netty.client.util.LogUtil;

public class CommandCMsgHandler extends BaseCommandHandler {

	@Override
	public void doHandle(Msg msg) {
		LogUtil.log("Client,handle msg:" + msg);
	}

}
