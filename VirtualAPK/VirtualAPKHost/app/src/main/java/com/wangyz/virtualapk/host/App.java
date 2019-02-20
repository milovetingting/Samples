package com.wangyz.virtualapk.host;

import android.app.Application;
import android.content.Context;

import com.didi.virtualapk.PluginManager;

/**
 * @author wangyz
 * @time 2019/2/18 17:02
 * @description App
 */
public class App extends Application{

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        PluginManager.getInstance(base).init();
    }
}
