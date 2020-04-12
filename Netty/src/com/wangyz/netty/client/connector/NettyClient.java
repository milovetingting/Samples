package com.wangyz.netty.client.connector;

import com.wangyz.netty.client.Config;
import com.wangyz.netty.client.bean.Msg;
import com.wangyz.netty.client.connection.IConnectionCallback;
import com.wangyz.netty.client.connection.IMsgCallback;
import com.wangyz.netty.client.util.LogUtil;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

public class NettyClient {

	private String mHost;

	private int mPort;

	private NettyClientHandler mClientHandler;

	private ChannelFuture mChannelFuture;

	private IConnectionCallback mConnectionCallback;

	public void setConnectionCallBack(IConnectionCallback callback) {
		mConnectionCallback = callback;
	}

	public NettyClient(String host, int port) {
		this.mHost = host;
		this.mPort = port;
	}

	public void connect() {
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			mClientHandler = new NettyClientHandler();
			b.group(workerGroup).channel(NioSocketChannel.class).option(ChannelOption.SO_KEEPALIVE, true)
					.handler(new ChannelInitializer<SocketChannel>() {

						@Override
						protected void initChannel(SocketChannel channel) throws Exception {
							channel.pipeline().addLast(new IdleStateHandler(60, 60, 60));
							channel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,
									Unpooled.copiedBuffer(Config.DATA_PACK_SEPARATOR.getBytes())));
							channel.pipeline().addLast("encoder", new StringEncoder());
							channel.pipeline().addLast("decoder", new StringDecoder());
							channel.pipeline().addLast(mClientHandler);
						}
					});
			mChannelFuture = b.connect(mHost, mPort).sync();
			if (mChannelFuture.isSuccess()) {
				LogUtil.log("Client,连接服务端成功");
				if (mConnectionCallback != null) {
					mConnectionCallback.onConnected();
				}
			}
			mChannelFuture.channel().closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			workerGroup.shutdownGracefully();
		}
	}

	public void sendMsg(Msg msg) {
		if (mChannelFuture == null || !mChannelFuture.isSuccess()) {
			throw new IllegalAccessError("please connect server first!");
		}
		mClientHandler.sendMsg(msg);
	}

	public void registerMsgCallback(IMsgCallback callback) {
		if (mClientHandler == null) {
			throw new IllegalAccessError("please connect server first!");
		}
		mClientHandler.registerMsgCallback(callback);
	}
}
