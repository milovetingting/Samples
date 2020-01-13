package com.wangyz.aspectjdemo;

import android.app.Application;
import android.util.Log;

import com.wangyz.library.TraceAspect;
import com.wangyz.library.TrackCallBack;
import com.wangyz.library.TrackPoint;

/**
 * @author wangyz
 * @time 2020/1/13 11:18
 * @description App
 */
public class App extends Application {

    private static final String TAG = TraceAspect.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        TrackPoint.init(new TrackCallBack() {
            @Override
            public void onClick(String pageName, String viewIdName) {
                Log.d(TAG, "onClick:" + pageName + "-" + viewIdName);
            }

            @Override
            public void onPageOpen(String pageName) {
                Log.d(TAG, "onPageOpen:" + pageName);
            }

            @Override
            public void onPageClose(String pageName) {
                Log.d(TAG, "onPageClose:" + pageName);
            }
        });
    }
}
