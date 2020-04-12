package com.wangyz.netty.server.dispatcher;

import java.util.UUID;

import com.wangyz.netty.server.Global;
import com.wangyz.netty.server.bean.Msg;
import com.wangyz.netty.server.bean.Msg.LoginResponseInfo;
import com.wangyz.netty.server.bean.Msg.LoginResponseInfo.ResponseData;
import com.wangyz.netty.server.util.MsgUtil;
import com.wangyz.netty.server.wrapper.ChannelHandlerContextWrapper;

public class LoginMsgHandler implements IMsgHandler {

	@Override
	public void handle(ChannelHandlerContextWrapper mChannelHandlerContextWrapper, Msg msg) {
		Msg responseMsg = new Msg();
		responseMsg.type = Msg.TYPE_LOGIN;
		Msg.LoginResponseInfo response = new LoginResponseInfo();
		response.code = Msg.LoginResponseInfo.CODE_SUCCESS;
		ResponseData data = new ResponseData();
		data.token = UUID.randomUUID().toString();
		response.data = Global.sGson.toJson(data);
		responseMsg.msg = Global.sGson.toJson(response);
		MsgUtil.sendMsg(mChannelHandlerContextWrapper, responseMsg);
	}

}
