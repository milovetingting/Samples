package com.wangyz.lib;

import android.util.Log;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author wangyz
 * @time 2020/5/15 16:36
 * @description 防抖
 */
public class AntiShakeUtil {

    public static void bind(Object target) {
        if (target == null) {
            return;
        }
        Class<?> clazz = target.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field == null || !field.isAnnotationPresent(AntiShake.class)) {
                continue;
            }
            try {
                field.setAccessible(true);
                View view = (View) field.get(target);

                Method getListenerInfo = getMethod(view.getClass(), "getListenerInfo");
                getListenerInfo.setAccessible(true);
                Object mListenerInfo = getListenerInfo.invoke(view);

                Field mOnClickListener = getField(mListenerInfo.getClass(), "mOnClickListener");
                mOnClickListener.setAccessible(true);
                View.OnClickListener onClickListener =
                        (View.OnClickListener) mOnClickListener.get(mListenerInfo);

                ClickInvocationHandler invocationHandler = new ClickInvocationHandler(onClickListener);
                Object proxyInstance = Proxy.newProxyInstance(AntiShakeUtil.class.getClassLoader(), new Class[]{View.OnClickListener.class}, invocationHandler);

                Method setOnClickListener = view.getClass().getMethod("setOnClickListener", View.OnClickListener.class);
                setOnClickListener.setAccessible(true);
                setOnClickListener.invoke(view, proxyInstance);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static Method getMethod(Class clazz, String methodName) {
        while (clazz != null) {
            try {
                return clazz.getDeclaredMethod(methodName);
            } catch (Exception e) {
                clazz = clazz.getSuperclass();
            }
        }
        throw new IllegalArgumentException("method:<" + methodName + "> not find!");
    }

    private static Field getField(Class clazz, String fieldName) {
        while (clazz != null) {
            try {
                return clazz.getDeclaredField(fieldName);
            } catch (Exception e) {
                clazz = clazz.getSuperclass();
            }
        }
        throw new IllegalArgumentException("field:<" + fieldName + "> not find!");
    }

    static class ClickInvocationHandler implements InvocationHandler {

        private View.OnClickListener listener;

        private long[] hits = new long[2];

        public ClickInvocationHandler(View.OnClickListener listener) {
            this.listener = listener;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Log.e("AntiShakeUtil", "Hook Click");
            if (hits[0] != 0) {
                System.arraycopy(hits, 0, hits, 1, hits.length - 1);
                hits[0] = System.currentTimeMillis();
                if (hits[0] - hits[hits.length - 1] < 500) {
                    return null;
                }
            } else {
                hits[0] = System.currentTimeMillis();
            }
            return method.invoke(listener, args);
        }
    }

}
