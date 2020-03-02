package com.wangyz.loader.loader;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wangyz.loader.interfaces.IImageLoader;

/**
 * @author wangyz
 * @time 2020/3/2 17:02
 * @description GlideLoader
 */
public class GlideLoader implements IImageLoader {
    @Override
    public void load(String url, ImageView view) {
        Glide.with(view).load(url).into(view);
    }
}
