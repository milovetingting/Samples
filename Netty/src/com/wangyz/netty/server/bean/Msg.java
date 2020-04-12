package com.wangyz.netty.server.bean;

public class Msg {

	/**
	 * 欢迎
	 */
	public static final int TYPE_WELCOME = 0;

	/**
	 * 心跳
	 */
	public static final int TYPE_HEART_BEAT = 1;

	/**
	 * 登录
	 */
	public static final int TYPE_LOGIN = 2;

	public static final int TYPE_COMMAND_A = 3;

	public static final int TYPE_COMMAND_B = 4;

	public static final int TYPE_COMMAND_C = 5;

	public int type;

	public String msg;

	public int priority;

	public long time;

	/**
	 * 登录请求信息
	 * 
	 * @author Administrator
	 *
	 */
	public static class LoginRuquestInfo {
		/**
		 * 用户名
		 */
		public String user;

		/**
		 * 密码
		 */
		public String pwd;

		@Override
		public String toString() {
			return "LoginRuquestInfo [user=" + user + ", pwd=" + pwd + "]";
		}
	}

	/**
	 * 登录响应信息
	 * 
	 * @author Administrator
	 *
	 */
	public static class LoginResponseInfo {

		/**
		 * 登录成功
		 */
		public static final int CODE_SUCCESS = 0;

		/**
		 * 登录失败
		 */
		public static final int CODE_FAILED = 100;

		/**
		 * 响应码
		 */
		public int code;

		/**
		 * 响应数据
		 */
		public String data;

		public static class ResponseData {
			public String token;
		}

		@Override
		public String toString() {
			return "LoginResponseInfo [code=" + code + ", data=" + data + "]";
		}

	}

	/**
	 * 指令信息
	 * 
	 * @author Administrator
	 *
	 */
	public static class Command {
		/**
		 * 指令id
		 */
		public String commandId;

		/**
		 * 指令内容
		 */
		public String command;

		@Override
		public String toString() {
			return "Command [commandId=" + commandId + ", command=" + command + "]";
		}

	}

	@Override
	public String toString() {
		return "Msg [type=" + type + ", msg=" + msg + ", priority=" + priority + ", time=" + time + "]";
	}

}
