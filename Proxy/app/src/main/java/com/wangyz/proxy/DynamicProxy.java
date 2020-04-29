package com.wangyz.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author wangyz
 * @time 2020/4/29 16:20
 * @description DynamicProxy
 */
public class DynamicProxy {

    private Player player;

    public DynamicProxy(Player player) {
        this.player = player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getProxy() {
        return (Player) Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{Player.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return method.invoke(player, args);
            }
        });
    }

}
