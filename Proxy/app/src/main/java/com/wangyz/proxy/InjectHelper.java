package com.wangyz.proxy;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author wangyz
 * @time 2020/4/29 9:03
 * @description InjectHelper
 */
public class InjectHelper {

    public static void inject(final Activity target) {
        if (target == null) {
            return;
        }
        Class<? extends Activity> clz = target.getClass();
        Method[] declaredMethods = clz.getDeclaredMethods();
        for (final Method method : declaredMethods) {
            if (method.isAnnotationPresent(OnClick.class)) {
                OnClick annotation = method.getAnnotation(OnClick.class);
                int[] resIds = annotation.value();
                for (int resId : resIds) {
                    final View view = target.findViewById(resId);
                    final Object proxyInstance = Proxy.newProxyInstance(InjectHelper.class.getClassLoader(), new Class[]{View.OnClickListener.class}, new InvocationHandler() {
                        @Override
                        public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
                            return method.invoke(target, view);
                        }
                    });
                    view.setOnClickListener((View.OnClickListener) proxyInstance);
                }
            } else if (method.isAnnotationPresent(OnLongClick.class)) {
                OnLongClick annotation = method.getAnnotation(OnLongClick.class);
                int[] resIds = annotation.value();
                for (int resId : resIds) {
                    final View view = target.findViewById(resId);
                    final Object proxyInstance = Proxy.newProxyInstance(InjectHelper.class.getClassLoader(), new Class[]{View.OnLongClickListener.class}, new InvocationHandler() {
                        @Override
                        public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
                            return method.invoke(target, view);
                        }
                    });
                    view.setOnLongClickListener((View.OnLongClickListener) proxyInstance);
                }
            }
        }
    }

}
