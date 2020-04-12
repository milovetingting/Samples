package com.wangyz.netty.client.dispatcher;

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

import com.wangyz.netty.client.bean.Msg;

public class MsgQueue {

	private PriorityBlockingQueue<Msg> mQueue;

	private boolean using;

	private MsgQueue() {
		mQueue = new PriorityBlockingQueue<>(128, new Comparator<Msg>() {

			@Override
			public int compare(Msg msg1, Msg msg2) {
				int res = msg2.priority - msg1.priority;
				if (res == 0 && msg1.time != msg2.time) {
					return (int) (msg2.time - msg1.time);
				}
				return res;
			}
		});
	}

	public static MsgQueue getInstance() {
		return MsgQueueInner.INSTANCE;
	}

	private static class MsgQueueInner {
		private static final MsgQueue INSTANCE = new MsgQueue();
	}

	/**
	 * 将消息加入消息队列
	 * 
	 * @param msg
	 */
	public void enqueueMsg(Msg msg) {
		mQueue.add(msg);
	}

	/**
	 * 从消息队列获取消息
	 * 
	 * @return
	 */
	public synchronized Msg next() {
		if (using) {
			return null;
		}
		Msg msg = mQueue.poll();
		if (msg != null) {
			makeUse(true);
		}
		return msg;
	}

	/**
	 * 标记使用状态
	 * 
	 * @param use
	 */
	public synchronized void makeUse(boolean use) {
		using = use;
	}

	/**
	 * 是否能够使用
	 * 
	 * @return
	 */
	public synchronized boolean canUse() {
		return !using;
	}

}
