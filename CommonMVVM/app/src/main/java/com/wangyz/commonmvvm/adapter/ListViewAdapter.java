package com.wangyz.commonmvvm.adapter;

import android.content.Context;

import com.wangyz.commonmvvm.base.BaseListViewAdapter;
import com.wangyz.commonmvvm.bean.entity.Project;

/**
 * @author wangyz
 * @time 2019/7/4 10:46
 * @description ListViewAdapter
 */
public class ListViewAdapter extends BaseListViewAdapter<Project> {

    public ListViewAdapter(Context context, int layoutId, int variableId) {
        super(context, layoutId, variableId);
    }

}
