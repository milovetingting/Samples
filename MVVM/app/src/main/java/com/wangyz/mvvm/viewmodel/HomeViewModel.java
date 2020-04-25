package com.wangyz.mvvm.viewmodel;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.wangyz.mvvm.Config;
import com.wangyz.mvvm.adapter.HomeAdapter;
import com.wangyz.mvvm.bean.Article;
import com.wangyz.mvvm.databinding.FragmentHomeBinding;
import com.wangyz.mvvm.datasource.HomeDataSourceFactory;

/**
 * @author wangyz
 * @time 2020/4/21 15:36
 * @description HomeViewModel
 */
public class HomeViewModel extends ViewModel {

    private LiveData<PagedList<Article>> listLiveData;

    public View view;

    public FragmentHomeBinding binding;

    public HomeAdapter adapter;

    public HomeViewModel() {
        HomeDataSourceFactory factory = new HomeDataSourceFactory();
        this.listLiveData = new LivePagedListBuilder<>(factory, Config.PAGE_SIZE).build();
    }

    public LiveData<PagedList<Article>> getListLiveData() {
        return listLiveData;
    }


}
