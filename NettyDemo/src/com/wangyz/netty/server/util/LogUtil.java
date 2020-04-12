package com.wangyz.netty.server.util;

import java.util.Date;

public class LogUtil {

	@SuppressWarnings("deprecation")
	public static void log(Object msg) {
		System.out.println(new Date().toLocaleString() + "--" + msg);
	}

}
