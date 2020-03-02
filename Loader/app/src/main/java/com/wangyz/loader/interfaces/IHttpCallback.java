package com.wangyz.loader.interfaces;

/**
 * @author wangyz
 * @time 2020/3/2 16:25
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
