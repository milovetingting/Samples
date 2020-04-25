package com.wangyz.jetpack.livedata;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.wangyz.jetpack.R;

/**
 * @author wangyz
 * @time 2020/4/13 14:48
 * @description LifeCycleActivity
 */
public class LiveDataActivity extends AppCompatActivity {

    private TextView tv;

    private LiveDataSub viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livedata);
        tv = findViewById(R.id.tv);
        viewModel = ViewModelProviders.of(this).get(LiveDataSub.class);
        viewModel.getInfo().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tv.setText(s);
            }
        });
    }

    public void update(View view) {
        String info = "info:" + viewModel.increaseNumber();
        viewModel.getInfo().setValue(info);
    }
}
