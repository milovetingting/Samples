package com.wangyz.shell.util;

import android.content.Context;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;

/**
 * @author wangyz
 * @time 2020/3/6 16:14
 * @description ClassLoaderUtil
 */
public class ClassLoaderUtil {

    /**
     * 加载Dex
     *
     * @param context
     * @param dexPaths
     */
    public static void loadDex(Context context, List<File> dexPaths) {

        if (dexPaths == null || dexPaths.isEmpty()) {
            return;
        }

        try {
            //获取自己的dexElements
            PathClassLoader pathClassLoader = (PathClassLoader) context.getClassLoader();

            Field pathListField = getField(pathClassLoader.getClass(), "pathList");
            Object pathListObject = pathListField.get(pathClassLoader);

            Field dexElementsField = getField(pathListObject.getClass(), "dexElements");
            Object[] dexElementsObject = (Object[]) dexElementsField.get(pathListObject);

            int dexLen = dexElementsObject.length;
            List<Object[]> dexElements = new ArrayList<>();
            dexElements.add(dexElementsObject);

            for (File dex : dexPaths) {
                //获取dex中的dexElements
                File odex = context.getDir("odex", Context.MODE_PRIVATE);
                DexClassLoader dexClassLoader = new DexClassLoader(dex.getAbsolutePath(), odex.getAbsolutePath(), null, pathClassLoader);

                Field pluginPathListField = getField(dexClassLoader.getClass(), "pathList");
                Object pluginPathListObject = pluginPathListField.get(dexClassLoader);

                Field pluginDexElementsField = getField(pluginPathListObject.getClass(), "dexElements");
                Object[] pluginDexElementsObject = (Object[]) pluginDexElementsField.get(pluginPathListObject);

                dexLen += pluginDexElementsObject.length;
                dexElements.add(pluginDexElementsObject);
            }

            Class<?> elementClazz = dexElementsObject.getClass().getComponentType();
            Object newDexElements = Array.newInstance(elementClazz, dexLen);

            int copiedLen = 0;
            for (Object[] objects : dexElements) {
                System.arraycopy(objects, 0, newDexElements, copiedLen, objects.length);
                copiedLen += objects.length;
            }

            //设置
            dexElementsField.set(pathListObject, newDexElements);

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

}
