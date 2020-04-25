package com.wangyz.mvvm.bean;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Objects;

/**
 * @author wangyz
 * @time 2020/4/21 16:06
 * @description Article
 */
public class Article {

    public int id;

    public String title;

    public String author;

    public String time;

    public String category;

    public boolean favorite;

    public String url;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Article article = (Article) o;
        return id == article.id &&
                favorite == article.favorite &&
                title.equals(article.title) &&
                author.equals(article.author) &&
                time.equals(article.time) &&
                category.equals(article.category) &&
                url.equals(article.url);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, time, category, favorite, url);
    }
}
