package com.wangyz.netty.client.util;

import java.util.Date;

public class LogUtil {

	@SuppressWarnings("deprecation")
	public static void log(Object msg) {
		System.out.println(new Date().toLocaleString() + "--" + msg);
	}

}
