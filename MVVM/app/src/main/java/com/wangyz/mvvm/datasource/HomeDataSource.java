package com.wangyz.mvvm.datasource;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.wangyz.mvvm.Config;
import com.wangyz.mvvm.bean.Article;
import com.wangyz.mvvm.bean.Home;
import com.wangyz.mvvm.model.network.callback.HttpCallback;
import com.wangyz.mvvm.model.network.helper.HttpHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangyz
 * @time 2020/4/21 15:24
 * @description HomeDataSource
 */
public class HomeDataSource extends PageKeyedDataSource<Integer, Article> {

    private int page;

    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback callback) {
        List<Article> list = getArticles(page);
        callback.onResult(list, page - 1, page);
    }

    @Override
    public void loadBefore(@NonNull LoadParams params, @NonNull LoadCallback callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams params, @NonNull LoadCallback callback) {
        page++;
        List<Article> list = getArticles(page);
        callback.onResult(list, page);
    }

    private List<Article> getArticles(int page) {
        final List<Article> articleList = new ArrayList<>();
        HttpHelper.getInstance().get((Config.URL_BASE + Config.URL_ARTICLE).replace("{page}", page + ""), null, new HttpCallback<Home>() {
            @Override
            public void onFailed(String msg) {
                Log.e(Config.TAG, msg);
            }

            @Override
            public void onSuccess(Home home) {
                if (home != null && home.getErrorCode() == 0) {
                    List<Home.DataBean.DatasBean> items = home.getData().getDatas();
                    for (Home.DataBean.DatasBean item : items) {
                        Article article = new Article();
                        article.title = item.getTitle();
                        article.author = item.getAuthor();
                        article.time = item.getNiceDate();
                        article.category = item.getChapterName();
                        article.favorite = item.isCollect();
                        article.url = item.getLink();
                        articleList.add(article);
                    }
                }
            }
        });
        return articleList;
    }
}
