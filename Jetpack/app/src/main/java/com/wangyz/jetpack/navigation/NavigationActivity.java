package com.wangyz.jetpack.navigation;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.wangyz.jetpack.R;

/**
 * @author wangyz
 * @time 2020/4/16 14:37
 * @description NavigationActivity
 */
public class NavigationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
    }
}
