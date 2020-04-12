package com.wangyz.netty.client.dispatcher;

import java.util.HashMap;
import java.util.Map;

import com.wangyz.netty.client.bean.Msg;
import com.wangyz.netty.client.handler.CommandAMsgHandler;
import com.wangyz.netty.client.handler.CommandBMsgHandler;
import com.wangyz.netty.client.handler.CommandCMsgHandler;
import com.wangyz.netty.client.handler.HeartBeatMsgHandler;
import com.wangyz.netty.client.handler.IMsgHandler;
import com.wangyz.netty.client.handler.WelcomeMsgHandler;

public class MsgDispatcher {

	private static Map<Integer, Class<? extends IMsgHandler>> mHandlerMap;

	static {
		mHandlerMap = new HashMap<>();
		mHandlerMap.put(Msg.TYPE_WELCOME, WelcomeMsgHandler.class);
		mHandlerMap.put(Msg.TYPE_HEART_BEAT, HeartBeatMsgHandler.class);
		mHandlerMap.put(Msg.TYPE_LOGIN, HeartBeatMsgHandler.class);
		mHandlerMap.put(Msg.TYPE_COMMAND_A, CommandAMsgHandler.class);
		mHandlerMap.put(Msg.TYPE_COMMAND_B, CommandBMsgHandler.class);
		mHandlerMap.put(Msg.TYPE_COMMAND_C, CommandCMsgHandler.class);
	}

	public static void dispatch() {
		if (MsgQueue.getInstance().canUse()) {
			Msg msg = MsgQueue.getInstance().next();
			if (msg == null) {
				return;
			}
			dispatch(msg);
		}
	}

	public static void dispatch(Msg msg) {
		try {
			IMsgHandler handler = (IMsgHandler) Class.forName(mHandlerMap.get(msg.type).getName()).newInstance();
			handler.handle(msg);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
