package com.wangyz.proxy;

import android.app.Activity;
import android.view.View;

import java.lang.annotation.Annotation;
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
        for (Method method : declaredMethods) {
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                Class<? extends Annotation> annotationType = annotation.annotationType();
                if (annotationType.isAnnotationPresent(EventType.class)) {
                    EventType eventType = annotationType.getAnnotation(EventType.class);
                    Class listenerType = eventType.listenerType();
                    String listenerSetter = eventType.listenerSetter();
                    try {
                        Method valueMethod = annotationType.getDeclaredMethod("value");
                        int[] ids = (int[]) valueMethod.invoke(annotation);
                        method.setAccessible(true);
                        ListenerInvocationHandler invocationHandler = new ListenerInvocationHandler(method, target);
                        Object proxyInstance = Proxy.newProxyInstance(target.getClassLoader(), new Class[]{listenerType}, invocationHandler);
                        for (int id : ids) {
                            View view = target.findViewById(id);
                            Method setter = view.getClass().getMethod(listenerSetter, listenerType);
                            setter.invoke(view, proxyInstance);
                        }
                    } catch (Exception e) {

                    }
                }
            }
        }
    }

    static class ListenerInvocationHandler<T> implements InvocationHandler {

        private Method method;

        private T target;

        public ListenerInvocationHandler(Method method, T target) {
            this.method = method;
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return this.method.invoke(target, args);
        }
    }

}
