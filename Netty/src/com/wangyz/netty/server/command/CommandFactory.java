package com.wangyz.netty.server.command;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.wangyz.netty.server.Global;
import com.wangyz.netty.server.bean.Msg;
import com.wangyz.netty.server.wrapper.ChannelHandlerContextWrapper;

public class CommandFactory {

	private static ThreadPoolExecutor mPool = new ThreadPoolExecutor(1, 1, 60, TimeUnit.SECONDS,
			new LinkedBlockingQueue<>(128));

	private static int[] mCommandTypes = { Msg.TYPE_COMMAND_A, Msg.TYPE_COMMAND_B, Msg.TYPE_COMMAND_C };

	private static Random mRandom = new Random();

	public static void pushCommand(ChannelHandlerContextWrapper wrapper) {

		final Random random = new Random();

		for (int i = 0; i < 100; i++) {
			mPool.execute(new Runnable() {

				@Override
				public void run() {
					try {
						Thread.sleep(5000);
						Msg msg = new Msg();
						msg.type = mCommandTypes[mRandom.nextInt(3)];
						msg.priority = random.nextInt(10);
						msg.time = System.currentTimeMillis();
						Msg.Command command = new Msg.Command();
						command.commandId = UUID.randomUUID().toString();
						command.command = "command " + msg.type;
						msg.msg = Global.sGson.toJson(command);
						wrapper.writeAndFlush(Global.sGson.toJson(msg));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});

		}
	}

}
