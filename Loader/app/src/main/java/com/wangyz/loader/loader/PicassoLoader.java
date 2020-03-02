package com.wangyz.loader.loader;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.wangyz.loader.interfaces.IImageLoader;

/**
 * @author wangyz
 * @time 2020/3/2 17:02
 * @description PicassoLoader
 */
public class PicassoLoader implements IImageLoader {
    @Override
    public void load(String url, ImageView view) {
        Picasso.get().load(url).into(view);
    }
}
