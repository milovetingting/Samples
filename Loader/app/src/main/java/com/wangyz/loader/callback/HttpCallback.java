package com.wangyz.loader.callback;

import com.google.gson.Gson;
import com.wangyz.loader.interfaces.IHttpCallback;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author wangyz
 * @time 2020/3/2 16:38
 * @description HttpCallback
 */
public abstract class HttpCallback<Result> implements IHttpCallback {

    @Override
    public void onSuccess(String result) {
        Gson gson = new Gson();
        Class<?> clazz = analysisClazzInfo(this);
        Result resultObj = (Result) gson.fromJson(result, clazz);
        onSuccess(resultObj);
    }

    /**
     * 成功时的回调
     *
     * @param result
     */
    public abstract void onSuccess(Result result);

    private Class<?> analysisClazzInfo(Object object) {
        Type genType = object.getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        return (Class<?>) params[0];
    }
}
