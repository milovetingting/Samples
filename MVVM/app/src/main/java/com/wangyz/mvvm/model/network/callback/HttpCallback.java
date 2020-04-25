package com.wangyz.mvvm.model.network.callback;

import com.google.gson.Gson;
import com.wangyz.mvvm.model.network.interfaces.IHttpCallback;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author wangyz
 * @time 2020/4/22 9:04
 * @description HttpCallback
 */
public abstract class HttpCallback<Result> implements IHttpCallback {

    @Override
    public void onSuccess(String result) {
        Gson gson = new Gson();
        Class<?> clazz = getClass(this);
        Result res = (Result) gson.fromJson(result, clazz);
        onSuccess(res);
    }

    /**
     * 成功时的回调
     *
     * @param result
     */
    public abstract void onSuccess(Result result);

    private Class<?> getClass(Object object) {
        Type type = object.getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) type).getActualTypeArguments();
        return (Class<?>) params[0];
    }
}
