package com.wangyz.mvvm.model.network.interfaces;

/**
 * @author wangyz
 * @time 2020/4/22 8:58
 * @description IHttpCallback
 */
public interface IHttpCallback {

    /**
     * 成功时的回调
     *
     * @param result
     */
    void onSuccess(String result);

    /**
     * 失败时的回调
     *
     * @param msg
     */
    void onFailed(String msg);


}
