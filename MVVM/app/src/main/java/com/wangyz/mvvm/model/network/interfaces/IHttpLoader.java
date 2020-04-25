package com.wangyz.mvvm.model.network.interfaces;

import java.util.Map;

/**
 * @author wangyz
 * @time 2020/4/22 8:57
 * @description IHttpLoader
 */
public interface IHttpLoader {

    /**
     * GET请求
     *
     * @param url      请求的url
     * @param params   请求的参数
     * @param callback 回调
     */
    void get(String url, Map<String, Object> params, IHttpCallback callback);

    /**
     * POST请求
     *
     * @param url      请求的url
     * @param params   请求的参数
     * @param callback 回调
     */
    void post(String url, Map<String, Object> params, IHttpCallback callback);

}
