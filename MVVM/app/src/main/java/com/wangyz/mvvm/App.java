package com.wangyz.mvvm;

import android.app.Application;

import com.wangyz.mvvm.model.network.helper.HttpHelper;
import com.wangyz.mvvm.model.network.loader.retrofit.RetrofitLoader;

/**
 * @author wangyz
 * @time 2020/4/22 9:17
 * @description App
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        HttpHelper.init(new RetrofitLoader());
    }
}
