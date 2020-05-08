package com.wangyz.ioc.runtime.util;

import android.view.View;

import com.wangyz.ioc.runtime.annotation.Event;
import com.wangyz.ioc.runtime.annotation.LayoutInject;
import com.wangyz.ioc.runtime.annotation.ViewInject;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author wangyz
 * @time 2020/5/8 9:20
 * @description InjectUtil
 */
public class InjectUtil {

    /**
     * 注入
     *
     * @param target
     */
    public static void inject(Object target) {
        if (target == null) {
            return;
        }
        injectLayout(target);
        injectView(target);
        injectEvent(target);
    }

    /**
     * 布局注入
     *
     * @param target 需要注入的组件
     */
    private static void injectLayout(Object target) {
        Class<?> clazz = target.getClass();
        boolean annotationPresent = clazz.isAnnotationPresent(LayoutInject.class);
        if (!annotationPresent) {
            return;
        }
        LayoutInject annotation = clazz.getAnnotation(LayoutInject.class);
        int layoutId = annotation.value();
        try {
            Method method = clazz.getMethod("setContentView", int.class);
            method.invoke(target, layoutId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 控件注入
     *
     * @param target 需要注入的组件
     */
    private static void injectView(Object target) {
        Class<?> clazz = target.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            boolean annotationPresent = field.isAnnotationPresent(ViewInject.class);
            if (!annotationPresent) {
                continue;
            }
            ViewInject annotation = field.getAnnotation(ViewInject.class);
            int viewId = annotation.value();
            try {
                Method method = clazz.getMethod("findViewById", int.class);
                View view = (View) method.invoke(target, viewId);
                field.setAccessible(true);
                field.set(target, view);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 事件注入
     *
     * @param target 需要注入的组件
     */
    private static void injectEvent(Object target) {
        Class<?> clazz = target.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                Class<? extends Annotation> annotationType = annotation.annotationType();
                boolean annotationPresent = annotationType.isAnnotationPresent(Event.class);
                if (!annotationPresent) {
                    continue;
                }
                Event event = annotationType.getAnnotation(Event.class);
                String listenerSetter = event.listenerSetter();
                Class<?> listenerType = event.listenerType();
                try {
                    Method valueMethod = annotationType.getDeclaredMethod("value");
                    valueMethod.setAccessible(true);
                    int[] viewIds = (int[]) valueMethod.invoke(annotation);
                    for (int viewId : viewIds) {
                        Method findViewByIdMethod = clazz.getMethod("findViewById", int.class);
                        View view = (View) findViewByIdMethod.invoke(target, viewId);
                        if (view == null) {
                            continue;
                        }
                        ListenerInvocationHandler listenerInvocationHandler = new ListenerInvocationHandler(target, method);
                        Object proxyInstance = Proxy.newProxyInstance(InjectUtil.class.getClassLoader(), new Class[]{listenerType}, listenerInvocationHandler);
                        Method listenerSetterMethod = view.getClass().getMethod(listenerSetter, listenerType);
                        listenerSetterMethod.setAccessible(true);
                        listenerSetterMethod.invoke(view, proxyInstance);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }

    static class ListenerInvocationHandler implements InvocationHandler {

        private Object target;

        private Method method;

        public ListenerInvocationHandler(Object target, Method method) {
            this.target = target;
            this.method = method;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return this.method.invoke(target, args);
        }
    }

}
