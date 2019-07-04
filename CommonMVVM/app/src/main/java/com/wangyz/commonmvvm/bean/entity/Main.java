package com.wangyz.commonmvvm.bean.entity;

import android.content.Intent;
import android.view.View;

import com.wangyz.commonmvvm.R;
import com.wangyz.commonmvvm.view.ListActivity;
import com.wangyz.commonmvvm.view.RecyclerActivity;

/**
 * @author wangyz
 * @time 2019/7/4 10:58
 * @description Main
 */
public class Main {

    /**
     * 点击事件
     *
     * @param view
     */
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_open_list: {
                Intent intent = new Intent(view.getContext(), ListActivity.class);
                view.getContext().startActivity(intent);
            }
            break;
            case R.id.btn_open_recycler: {
                Intent intent = new Intent(view.getContext(), RecyclerActivity.class);
                view.getContext().startActivity(intent);
            }
            break;
            default:
                break;
        }
    }

}
