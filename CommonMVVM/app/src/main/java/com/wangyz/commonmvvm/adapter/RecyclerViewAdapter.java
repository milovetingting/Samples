package com.wangyz.commonmvvm.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.ViewGroup;

import com.wangyz.commonmvvm.BR;
import com.wangyz.commonmvvm.R;
import com.wangyz.commonmvvm.base.BaseRecyclerViewAdapter;
import com.wangyz.commonmvvm.bean.entity.Project;

/**
 * @author wangyz
 * @time 2019/7/4 13:55
 * @description RecyclerViewAdapter
 */
public class RecyclerViewAdapter extends BaseRecyclerViewAdapter<Project, BindingVH> {
    @Override
    public BindingVH createVH(ViewGroup parent, int viewType) {
        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(inflater, R.layout.item_recycler,
                parent, false);
        return new BindingVH<>(viewDataBinding);
    }

    @Override
    public void bindVH(BindingVH bindingVH, int position) {
        bindingVH.getBinding().setVariable(BR.project, data.get(position));
    }
}
