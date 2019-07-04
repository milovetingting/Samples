package com.wangyz.commonmvvm.view;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wangyz.commonmvvm.BR;
import com.wangyz.commonmvvm.R;
import com.wangyz.commonmvvm.adapter.ListViewAdapter;
import com.wangyz.commonmvvm.base.BaseActivity;
import com.wangyz.commonmvvm.databinding.ActivityListBinding;
import com.wangyz.commonmvvm.vm.ListViewVM;

/**
 * @author wangyz
 * @time 2019/7/4 13:12
 * @description ListActivity
 */
public class ListActivity extends BaseActivity<ActivityListBinding> implements
        OnLoadMoreListener, OnRefreshListener {

    private ListViewVM mVm;

    @Override
    public int getLayoutId() {
        return R.layout.activity_list;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        ListViewAdapter adapter = new ListViewAdapter(context, R.layout.item_list, BR.project);
        viewDataBinding.lv.setAdapter(adapter);
        viewDataBinding.refreshLayout.setOnRefreshListener(this);
        viewDataBinding.refreshLayout.setOnLoadMoreListener(this);
        mVm = new ListViewVM(context, viewDataBinding, adapter);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mVm.refresh();
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        mVm.load();
    }

}
