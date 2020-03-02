package com.wangyz.loader.helper;

import com.wangyz.loader.interfaces.IHttpCallback;
import com.wangyz.loader.interfaces.IHttpLoader;

import java.util.Map;

/**
 * @author wangyz
 * @time 2020/3/2 16:42
 * @description HttpHelper
 */
public class HttpHelper implements IHttpLoader {

    private static IHttpLoader mHttpLoader;

    private HttpHelper() {

    }

    public static void init(IHttpLoader httpLoader) {
        mHttpLoader = httpLoader;
    }

    public static HttpHelper obtain() {
        return HttpHelperHolder.INSTANCE;
    }

    static class HttpHelperHolder {
        private static HttpHelper INSTANCE = new HttpHelper();
    }

    @Override
    public void get(String url, Map<String, Object> params, IHttpCallback callback) {
        mHttpLoader.get(url, params, callback);
    }

    @Override
    public void post(String url, Map<String, Object> params, IHttpCallback callback) {
        mHttpLoader.post(url, params, callback);
    }
}
