package com.wangyz.loader;

import android.app.Application;

import com.wangyz.loader.helper.HttpHelper;
import com.wangyz.loader.helper.ImageHelper;
import com.wangyz.loader.loader.GlideLoader;
import com.wangyz.loader.loader.VolleyHttpLoader;

/**
 * @author wangyz
 * @time 2020/3/2 16:48
 * @description App
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        HttpHelper.init(new OkHttpLoader());
        HttpHelper.init(new VolleyHttpLoader(this));

//        ImageHelper.init(new PicassoLoader());
        ImageHelper.init(new GlideLoader());
    }
}
