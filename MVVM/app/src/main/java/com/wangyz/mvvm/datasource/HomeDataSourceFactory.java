package com.wangyz.mvvm.datasource;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;

import com.wangyz.mvvm.bean.Article;

/**
 * @author wangyz
 * @time 2020/4/21 15:35
 * @description HomeDataSourceFactory
 */
public class HomeDataSourceFactory extends DataSource.Factory<Integer, Article> {
    @NonNull
    @Override
    public DataSource<Integer, Article> create() {
        HomeDataSource dataSource = new HomeDataSource();
        return dataSource;
    }
}
