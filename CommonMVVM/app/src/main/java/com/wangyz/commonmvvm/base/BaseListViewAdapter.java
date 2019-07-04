package com.wangyz.commonmvvm.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangyz
 * @time 2019/7/3 10:30
 * @description BaseListViewAdapter
 */
public abstract class BaseListViewAdapter<E> extends BaseAdapter {

    public Context mContext;
    public LayoutInflater mInflater;
    public int mLayoutId;
    public int mVariableId;
    public List<E> mList = new ArrayList<>();

    public BaseListViewAdapter(Context context, int layoutId, int variableId) {
        mContext = context;
        mLayoutId = layoutId;
        mVariableId = variableId;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewDataBinding dataBinding;
        if (convertView == null) {
            dataBinding = DataBindingUtil.inflate(mInflater, mLayoutId, parent, false);
        } else {
            dataBinding = DataBindingUtil.getBinding(convertView);
        }
        dataBinding.setVariable(mVariableId, mList.get(position));
        return dataBinding.getRoot();
    }

    /**
     * 刷新
     *
     * @param data list data
     */
    public void refreshData(List<E> data) {
        this.mList.clear();
        this.mList.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * 加载更多
     *
     * @param data list data
     */
    public void loadMoreData(List<E> data) {
        this.mList.addAll(data);
        notifyDataSetChanged();
    }

}
