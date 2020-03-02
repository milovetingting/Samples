package com.wangyz.loader.interfaces;

import java.util.Map;

/**
 * @author wangyz
 * @time 2020/3/2 16:24
 * @description IHttpLoader
 */
public interface IHttpLoader {

    /**
     * GET方式请求
     *
     * @param url
     * @param params
     * @param callback
     */
    void get(String url, Map<String, Object> params, IHttpCallback callback);

    /**
     * POST方式请求
     *
     * @param url
     * @param params
     * @param callback
     */
    void post(String url, Map<String, Object> params, IHttpCallback callback);

}
