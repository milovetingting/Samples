package com.wangyz.common;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * @author wangyz
 * @time 2019/2/1 9:47
 * @description BaseApplication
 */
public class BaseApplication extends Application {

    private boolean isDebugARouter = true;

    @Override
    public void onCreate() {
        super.onCreate();

        if (isDebugARouter) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
    }
}
