package com.wangyz.modules;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * @author wangyz
 * @time 2019/1/31 17:30
 * @description MainActivity
 */
public class MainActivity extends FragmentActivity {

    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFragmentManager = getSupportFragmentManager();
        Fragment fragment = (Fragment) ARouter.getInstance().build("/home/fragment").navigation();
        mFragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
    }

    public void home(View view) {
        Fragment fragment = (Fragment) ARouter.getInstance().build("/home/fragment").navigation();
        mFragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
    }

    public void project(View view) {
        Fragment fragment = (Fragment) ARouter.getInstance().build("/project/fragment").navigation();
        mFragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
    }

    public void user(View view) {
        Fragment fragment = (Fragment) ARouter.getInstance().build("/user/fragment").navigation();
        mFragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
    }
}
