package com.wangyz.loader.interfaces;

import android.widget.ImageView;

/**
 * @author wangyz
 * @time 2020/3/2 16:26
 * @description IImageLoader
 */
public interface IImageLoader {

    /**
     * 加载图片
     *
     * @param url
     * @param view
     */
    void load(String url, ImageView view);

}
