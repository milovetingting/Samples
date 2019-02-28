package com.wangyz.project;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * @author wangyz
 * @time 2019/2/1 8:49
 * @description MainActivity
 */
public class MainActivity extends FragmentActivity {

    private FragmentManager mFragmentManager;

    private FragmentTransaction mTransaction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragmentManager = getSupportFragmentManager();
        mTransaction = mFragmentManager.beginTransaction();
        ProjectFragment homeFragment = new ProjectFragment();
        mTransaction.replace(R.id.container, homeFragment);
        mTransaction.commit();

    }
}
