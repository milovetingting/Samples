package com.wangyz.plugindemo;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;

/**
 * @author wangyz
 * @time 2020/3/6 16:14
 * @description HookUtil
 */
public class HookUtil {

    /**
     * 加载插件
     *
     * @param context
     * @param dexPath
     */
    public static void loadPlugin(Context context, String dexPath) {

        //判断dex是否存在
        File dex = new File(dexPath);
        if (!dex.exists()) {
            return;
        }

        try {
            //获取自己的dexElements
            PathClassLoader pathClassLoader = (PathClassLoader) context.getClassLoader();

            Field pathListField = getField(pathClassLoader.getClass(), "pathList");
            Object pathListObject = pathListField.get(pathClassLoader);

            Field dexElementsField = getField(pathListObject.getClass(), "dexElements");
            Object[] dexElementsObject = (Object[]) dexElementsField.get(pathListObject);

            //获取dex中的dexElements
            File odex = context.getDir("odex", Context.MODE_PRIVATE);
            DexClassLoader dexClassLoader = new DexClassLoader(dexPath, odex.getAbsolutePath(), null, pathClassLoader);

            Field pluginPathListField = getField(dexClassLoader.getClass(), "pathList");
            Object pluginPathListObject = pluginPathListField.get(dexClassLoader);

            Field pluginDexElementsField = getField(pluginPathListObject.getClass(), "dexElements");
            Object[] pluginDexElementsObject = (Object[]) pluginDexElementsField.get(pluginPathListObject);

            Class<?> elementClazz = dexElementsObject.getClass().getComponentType();
            Object newDexElements = Array.newInstance(elementClazz, pluginDexElementsObject.length + dexElementsObject.length);
            System.arraycopy(pluginDexElementsObject, 0, newDexElements, 0, pluginDexElementsObject.length);
            System.arraycopy(dexElementsObject, 0, newDexElements, pluginDexElementsObject.length, dexElementsObject.length);

            //设置
            dexElementsField.set(pathListObject, newDexElements);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void hookAMS() {
        try {
            Field singletonField;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                singletonField = getField(Class.forName("android.app.ActivityManager"), "IActivityManagerSingleton");
            } else {
                singletonField = getField(Class.forName("android.app.ActivityManagerNative"), "gDefault");
            }
            Object singleton = singletonField.get(null);

            Field mInstanceField = getField(Class.forName("android.util.Singleton"), "mInstance");
            final Object mInstance = mInstanceField.get(singleton);

            final Object proxyInstance = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{Class.forName("android.app.IActivityManager")}, new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    if ("startActivity".equals(method.getName())) {
                        int index = 0;
                        for (int i = 0; i < args.length; i++) {
                            if (args[i] instanceof Intent) {
                                index = i;
                                break;
                            }
                        }
                        Intent intent = (Intent) args[index];

                        Intent proxyIntent = new Intent(intent);
                        proxyIntent.setClassName("com.wangyz.plugindemo", "com.wangyz.plugindemo.ProxyActivity");
                        proxyIntent.putExtra("target_intent", intent);

                        args[index] = proxyIntent;
                    }
                    return method.invoke(mInstance, args);
                }
            });
            mInstanceField.set(singleton, proxyInstance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void hookHandler() {
        try {
            Field sCurrentActivityThreadThread = getField(Class.forName("android.app.ActivityThread"), "sCurrentActivityThread");
            Object activityThread = sCurrentActivityThreadThread.get(null);

            Field mHField = getField(Class.forName("android.app.ActivityThread"), "mH");
            Object mH = mHField.get(activityThread);

            Field mCallbackField = getField(Class.forName("android.os.Handler"), "mCallback");
            mCallbackField.set(mH, new Handler.Callback() {
                @Override
                public boolean handleMessage(Message msg) {
                    switch (msg.what) {
                        case 100: {
                            try {
                                Field intentField = getField(msg.obj.getClass(), "intent");
                                Intent proxyIntent = (Intent) intentField.get(msg.obj);
                                Intent targetIntent = proxyIntent.getParcelableExtra("target_intent");
                                if (targetIntent != null) {
//                                    proxyIntent.setComponent(targetIntent.getComponent());
                                    intentField.set(msg.obj, targetIntent);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                        break;
                        case 159: {
                            try {
                                Field mActivityCallbacksField = getField(msg.obj.getClass(), "mActivityCallbacks");
                                List mActivityCallbacks = (List) mActivityCallbacksField.get(msg.obj);
                                for (int i = 0; i < mActivityCallbacks.size(); i++) {
                                    if (mActivityCallbacks.get(i).getClass().getName()
                                            .equals("android.app.servertransaction.LaunchActivityItem")) {
                                        Object launchActivityItem = mActivityCallbacks.get(i);

                                        Field mIntentField = getField(launchActivityItem.getClass(), "mIntent");
                                        Intent intent = (Intent) mIntentField.get(launchActivityItem);
                                        // 获取插件的
                                        Intent proxyIntent = intent.getParcelableExtra("target_intent");
                                        //替换
                                        if (proxyIntent != null) {
                                            mIntentField.set(launchActivityItem, proxyIntent);
                                        }
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                        default:
                            break;
                    }
                    return false;
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取Field
     *
     * @param clazz
     * @param fieldName
     * @return
     */
    private static Field getField(Class clazz, String fieldName) {
        Field field;
        while (clazz != null) {
            try {
                field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                return field;
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            }
        }
        return null;
    }

    /**
     * 获取Method
     *
     * @param clazz
     * @param methodName
     * @return
     */
    private static Method getMethod(Class clazz, String methodName) {
        Method method;
        while (clazz != null) {
            try {
                method = clazz.getDeclaredMethod(methodName);
                method.setAccessible(true);
                return method;
            } catch (NoSuchMethodException e) {
                clazz = clazz.getSuperclass();
            }
        }
        return null;
    }

}
