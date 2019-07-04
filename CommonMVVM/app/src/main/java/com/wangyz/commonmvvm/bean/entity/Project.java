package com.wangyz.commonmvvm.bean.entity;

import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wangyz.commonmvvm.view.ArticleActivity;

/**
 * @author wangyz
 * @time 2019/7/3 17:07
 * @description Project
 */
public class Project extends BaseObservable {

    private String imageUrl;

    private String desc;

    private String url;

    @Bindable
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        notifyChange();
    }

    @Bindable
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
        notifyChange();
    }

    @Bindable
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
        notifyChange();
    }

    public Project(String imageUrl, String desc, String url) {
        this.imageUrl = imageUrl;
        this.desc = desc;
        this.url = url;
    }

    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView iv, String imageUrl) {
        Glide.with(iv).load(imageUrl).into(iv);
    }

    public void onItemClick(View view) {
        Intent intent = new Intent(view.getContext(), ArticleActivity.class);
        intent.putExtra("link", url);
        view.getContext().startActivity(intent);
    }
}
