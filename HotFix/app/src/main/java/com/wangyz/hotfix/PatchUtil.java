package com.wangyz.hotfix;

import android.content.Context;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Field;

import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;

/**
 * @author wangyz
 * @time 2020/2/19 10:15
 * @description PatchUtil
 */
public class PatchUtil {

    /**
     * 加载patch
     *
     * @param context
     * @param patch
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static void loadPatch(Context context, String patch) throws NoSuchFieldException,
            IllegalAccessException {

        //如果patch不存在，直接返回
        File patchFile = new File(patch);
        if (!patchFile.exists()) {
            return;
        }

        //获取系统的PathClassLoader
        PathClassLoader pathClassLoader = (PathClassLoader) context.getClassLoader();

        //获取BaseDexClassLoader中DexPathList类型的属性:pathList
        Field pathListField = pathClassLoader.getClass().getSuperclass().getDeclaredField(
                "pathList");
        pathListField.setAccessible(true);
        Object pathListObject = pathListField.get(pathClassLoader);

        //获取DexPathList中Element[]类型的dexElements
        Field dexElementsField = pathListObject.getClass().getDeclaredField("dexElements");
        dexElementsField.setAccessible(true);
        Object dexElementsObject = dexElementsField.get(pathListObject);

        //设置optimizedDirectory
        File odex = context.getDir("odex", Context.MODE_PRIVATE);
        //创建自定义的DexClassLoader
        DexClassLoader dexClassLoader = new DexClassLoader(patch, odex.getAbsolutePath(), null,
                context.getClassLoader());
        //获取BaseDexClassLoader中DexPathList类型的属性:pathList
        Field patchPathListField = dexClassLoader.getClass().getSuperclass().getDeclaredField(
                "pathList");
        patchPathListField.setAccessible(true);
        Object patchPathListObject = patchPathListField.get(dexClassLoader);

        //获取DexPathList中Element[]类型的dexElements
        Field patchDexElementsField = patchPathListObject.getClass().getDeclaredField(
                "dexElements");
        patchDexElementsField.setAccessible(true);
        Object patchDexElementsObject = patchDexElementsField.get(patchPathListObject);

        //合并数组
        Class<?> elementClazz = dexElementsObject.getClass().getComponentType();
        int dexElementsSize = Array.getLength(dexElementsObject);
        int patchDexElementsSize = Array.getLength(patchDexElementsObject);
        int newDexElementsSize = dexElementsSize + patchDexElementsSize;
        Object newDexElements = Array.newInstance(elementClazz, newDexElementsSize);
        for (int i = 0; i < newDexElementsSize; i++) {
            if (i < patchDexElementsSize) {
                Array.set(newDexElements, i, Array.get(patchDexElementsObject, i));
            } else {
                Array.set(newDexElements, i, Array.get(dexElementsObject,
                        i - patchDexElementsSize));
            }
        }

        //替换原来的dexElements
        dexElementsField.set(pathListObject, newDexElements);
    }

}
