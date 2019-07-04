package com.wangyz.commonmvvm.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.wangyz.commonmvvm.R;
import com.wangyz.commonmvvm.adapter.RecyclerViewAdapter;
import com.wangyz.commonmvvm.base.BaseActivity;
import com.wangyz.commonmvvm.custom.SpaceItemDecoration;
import com.wangyz.commonmvvm.databinding.ActivityRecyclerBinding;
import com.wangyz.commonmvvm.vm.RecyclerViewVM;

/**
 * @author wangyz
 * @time 2019/7/4 13:45
 * @description RecyclerActivity
 */
public class RecyclerActivity extends BaseActivity<ActivityRecyclerBinding> implements
        OnRefreshLoadMoreListener {

    private RecyclerViewVM mVm;

    @Override
    public int getLayoutId() {
        return R.layout.activity_recycler;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        viewDataBinding.recycler.setLayoutManager(layoutManager);
        viewDataBinding.recycler.addItemDecoration(new SpaceItemDecoration(5));
        RecyclerViewAdapter adapter = new RecyclerViewAdapter();
        viewDataBinding.recycler.setAdapter(adapter);
        viewDataBinding.refreshLayout.setOnRefreshLoadMoreListener(this);
        viewDataBinding.refreshLayout.setOnLoadMoreListener(this);
        mVm = new RecyclerViewVM(context, viewDataBinding, adapter);
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        mVm.load();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mVm.refresh();
    }
}
