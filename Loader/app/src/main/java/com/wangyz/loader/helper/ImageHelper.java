package com.wangyz.loader.helper;

import android.widget.ImageView;

import com.wangyz.loader.interfaces.IImageLoader;

/**
 * @author wangyz
 * @time 2020/3/2 17:03
 * @description ImageHelper
 */
public class ImageHelper implements IImageLoader {

    private static IImageLoader mImageLoader;

    private ImageHelper() {

    }

    public static void init(IImageLoader imageLoader) {
        mImageLoader = imageLoader;
    }

    public static ImageHelper obtain() {
        return ImageHelperHolder.INSTANCE;
    }

    static class ImageHelperHolder {
        private static final ImageHelper INSTANCE = new ImageHelper();
    }

    @Override
    public void load(String url, ImageView view) {
        mImageLoader.load(url, view);
    }
}
