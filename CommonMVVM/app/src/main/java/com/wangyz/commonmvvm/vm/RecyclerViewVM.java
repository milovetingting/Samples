package com.wangyz.commonmvvm.vm;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.widget.Toast;

import com.wangyz.commonmvvm.adapter.BindingVH;
import com.wangyz.commonmvvm.base.BaseRecyclerViewAdapter;
import com.wangyz.commonmvvm.bean.entity.Project;
import com.wangyz.commonmvvm.databinding.ActivityRecyclerBinding;
import com.wangyz.commonmvvm.model.base.IProjectModel;
import com.wangyz.commonmvvm.model.impl.ProjectModelImpl;

import java.util.List;

/**
 * @author wangyz
 * @time 2019/7/3 17:34
 * @description ProjectVM
 */
public class RecyclerViewVM<VDB extends ViewDataBinding> implements IProjectModel.LoadCallBack {

    private Context mContext;

    private VDB mBinding;

    private BaseRecyclerViewAdapter mAdapter;

    private IProjectModel mModel;

    private int mPageNum = 1;

    public RecyclerViewVM(Context context, VDB binding, BaseRecyclerViewAdapter<Project, BindingVH>
            adapter) {
        mContext = context;
        mBinding = binding;
        mAdapter = adapter;
        init();
    }

    private void init() {
        mModel = new ProjectModelImpl();
        refresh();
    }

    /**
     * 加载
     */
    public void load() {
        mPageNum++;
        mModel.load(mPageNum, this);
    }

    /**
     * 刷新
     */
    public void refresh() {
        mPageNum = 1;
        mModel.load(mPageNum, this);
    }


    @Override
    public void loadSuccess(List<Project> data) {
        if (mPageNum > 1) {
            ((ActivityRecyclerBinding) mBinding).refreshLayout.finishLoadMore();
            mAdapter.loadMoreData(data);
        } else {
            ((ActivityRecyclerBinding) mBinding).refreshLayout.finishRefresh();
            mAdapter.refreshData(data);
        }
    }

    @Override
    public void loadFailed(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
        if (mPageNum > 1) {
            ((ActivityRecyclerBinding) mBinding).refreshLayout.finishLoadMore();
        } else {
            ((ActivityRecyclerBinding) mBinding).refreshLayout.finishRefresh();
        }
    }
}
