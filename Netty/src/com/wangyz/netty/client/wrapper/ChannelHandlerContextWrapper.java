package com.wangyz.netty.client.wrapper;

import com.wangyz.netty.client.Config;

import io.netty.channel.ChannelHandlerContext;

public class ChannelHandlerContextWrapper {

	private ChannelHandlerContext mContext;

	public ChannelHandlerContextWrapper(ChannelHandlerContext context) {
		this.mContext = context;
	}

	/**
	 * 包装writeAndFlush方法
	 * 
	 * @param object
	 */
	public void writeAndFlush(Object object) {
		mContext.writeAndFlush(object + Config.DATA_PACK_SEPARATOR);
	}

}
