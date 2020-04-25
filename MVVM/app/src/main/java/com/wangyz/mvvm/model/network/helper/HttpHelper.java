package com.wangyz.mvvm.model.network.helper;

import com.wangyz.mvvm.model.network.interfaces.IHttpCallback;
import com.wangyz.mvvm.model.network.interfaces.IHttpLoader;

import java.util.Map;

/**
 * @author wangyz
 * @time 2020/4/22 9:10
 * @description HttpHelper
 */
public class HttpHelper implements IHttpLoader {

    private static IHttpLoader mHttpLoader;

    private HttpHelper() {

    }

    /**
     * 获取HttpHelper单例
     *
     * @return
     */
    public static HttpHelper getInstance() {
        return HttpHelperHolder.INSTANCE;
    }

    /**
     * 初始化
     *
     * @param httpLoader
     */
    public static void init(IHttpLoader httpLoader) {
        mHttpLoader = httpLoader;
    }

    public static class HttpHelperHolder {
        private static HttpHelper INSTANCE = new HttpHelper();
    }

    @Override
    public void get(String url, Map<String, Object> params, IHttpCallback callback) {
        checkInit();
        mHttpLoader.get(url, params, callback);
    }

    @Override
    public void post(String url, Map<String, Object> params, IHttpCallback callback) {
        checkInit();
        mHttpLoader.post(url, params, callback);
    }

    private void checkInit() {
        if (mHttpLoader == null) {
            throw new IllegalArgumentException("please init first");
        }
    }
}
