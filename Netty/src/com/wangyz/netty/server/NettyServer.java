package com.wangyz.netty.server;

import com.wangyz.netty.server.util.LogUtil;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

public class NettyServer {

	private int mPort;

	public NettyServer(int port) {
		this.mPort = port;
	}

	public void run() {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, 128)
					.childOption(ChannelOption.SO_KEEPALIVE, true)
					.childHandler(new ChannelInitializer<SocketChannel>() {

						@Override
						protected void initChannel(SocketChannel channel) throws Exception {
							channel.pipeline().addLast(new IdleStateHandler(60, 60, 60));
							channel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,
									Unpooled.copiedBuffer(Config.DATA_PACK_SEPARATOR.getBytes())));
							channel.pipeline().addLast("encoder", new StringEncoder());
							channel.pipeline().addLast("decoder", new StringDecoder());
							channel.pipeline().addLast(new NettyServerHandler());
						}
					});
			ChannelFuture f = b.bind(mPort).sync();
			if (f.isSuccess()) {
				LogUtil.log("Server,启动Netty服务端成功，端口号:" + mPort);
			}
			// f.channel().closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// workerGroup.shutdownGracefully();
			// bossGroup.shutdownGracefully();
		}
	}

}
