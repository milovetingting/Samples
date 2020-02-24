package com.wangyz.hotfix;

import android.app.Application;

/**
 * @author wangyz
 * @time 2020/2/19 11:09
 * @description App
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            PatchUtil.loadPatch(getApplicationContext(), "/sdcard/patch.dex");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
