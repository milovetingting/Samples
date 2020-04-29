package com.wangyz.proxy;

/**
 * @author wangyz
 * @time 2020/4/29 16:15
 * @description ProxyImpl
 */
public class ProxyImpl implements Player {

    private Player player;

    public ProxyImpl(Player player) {
        this.player = player;
    }

    @Override
    public void play() {
        player.play();
    }
}
