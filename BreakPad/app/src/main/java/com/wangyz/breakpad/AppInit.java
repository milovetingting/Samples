package com.wangyz.breakpad;

import android.app.Application;

import com.wangyz.breakpad.crash.CrashUtil;

public class AppInit extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CrashUtil.init(this);
    }
}
