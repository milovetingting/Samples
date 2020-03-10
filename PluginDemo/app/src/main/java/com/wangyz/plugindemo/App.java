package com.wangyz.plugindemo;

import android.app.Application;
import android.content.Context;

import java.io.File;

/**
 * @author wangyz
 * @time 2020/3/6 16:56
 * @description App
 */
public class App extends Application {

    private static final String PLUGIN_PATH = "/sdcard/plugin.apk";

    @Override
    public void onCreate() {
        super.onCreate();
        //一般是从服务器下载回来，然后复制到应用的私有目录下，这里演示从sdcard复制到data目录下,6.0及以上需要申请动态权限。复制应该放在非UI线程上做，这里简化操作，放在UI线程上操作。
        String pluginPath = getDir("plugin", Context.MODE_PRIVATE).getAbsolutePath();
        pluginPath = pluginPath + "/plugin.apk";
        if (!new File(pluginPath).exists()) {
            FileUtil.copyFile(PLUGIN_PATH, pluginPath);
        }
        HookUtil.loadPlugin(this, pluginPath);
        HookUtil.hookAMS();
        HookUtil.hookHandler();
    }
}
