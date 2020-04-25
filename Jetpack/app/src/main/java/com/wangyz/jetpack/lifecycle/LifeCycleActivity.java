package com.wangyz.jetpack.lifecycle;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.wangyz.jetpack.R;

/**
 * @author wangyz
 * @time 2020/4/13 14:48
 * @description LifeCycleActivity
 */
public class LifeCycleActivity extends AppCompatActivity {

    LifecycleObserverImpl observer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);
        observer = new LifecycleObserverImpl();
        getLifecycle().addObserver(observer);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getLifecycle().removeObserver(observer);
    }
}
