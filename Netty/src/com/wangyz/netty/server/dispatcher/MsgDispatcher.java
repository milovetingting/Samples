package com.wangyz.netty.server.dispatcher;

import java.util.HashMap;
import java.util.Map;

import com.wangyz.netty.server.bean.Msg;
import com.wangyz.netty.server.wrapper.ChannelHandlerContextWrapper;

public class MsgDispatcher {

	private static Map<Integer, Class<? extends IMsgHandler>> mHandlerMap;

	static {
		mHandlerMap = new HashMap<>();
		mHandlerMap.put(Msg.TYPE_HEART_BEAT, HeartBeatMsgHandler.class);
		mHandlerMap.put(Msg.TYPE_LOGIN, LoginMsgHandler.class);
	}

	public static void dispatch(ChannelHandlerContextWrapper mChannelHandlerContextWrapper, Msg msg) {
		try {
			IMsgHandler handler = (IMsgHandler) Class.forName(mHandlerMap.get(msg.type).getName()).newInstance();
			handler.handle(mChannelHandlerContextWrapper, msg);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
