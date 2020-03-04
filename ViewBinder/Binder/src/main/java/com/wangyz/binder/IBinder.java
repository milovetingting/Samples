package com.wangyz.binder;

/**
 * @author wangyz
 * @time 2020/3/3 8:59
 * @description IBinder
 */
public interface IBinder<T> {

    /**
     * 绑定activity
     *
     * @param t
     */
    void bind(T t);

}
