package com.wangyz.commonmvvm.view;

import android.os.Bundle;

import com.wangyz.commonmvvm.BR;
import com.wangyz.commonmvvm.R;
import com.wangyz.commonmvvm.base.BaseActivity;
import com.wangyz.commonmvvm.bean.entity.Main;
import com.wangyz.commonmvvm.databinding.ActivityMainBinding;

/**
 * @author wangyz
 */
public class MainActivity extends BaseActivity<ActivityMainBinding> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        viewDataBinding.setVariable(BR.main, new Main());
    }
}
