package com.wangyz.plugin;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.lang.reflect.Method;

/**
 * @author wangyz
 * @time 2020/3/9 15:52
 * @description ResourceManager
 */
public class ResourceManager {

    private static final String PATH = "/data/user/0/com.wangyz.plugindemo/app_plugin/plugin.apk";

    private static Resources mResources;

    public static Resources getResources(Context context) {
        if (mResources == null) {
            mResources = loadResource(context);
        }
        return mResources;
    }

    private static Resources loadResource(Context context) {
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPathField = assetManager.getClass().getDeclaredMethod("addAssetPath", String.class);
            addAssetPathField.setAccessible(true);
            addAssetPathField.invoke(assetManager, PATH);
            Resources resources = context.getResources();
            return new Resources(assetManager, resources.getDisplayMetrics(), resources.getConfiguration());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
