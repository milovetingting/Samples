package com.wangyz.viewbinder;

import android.os.Bundle;
import android.widget.TextView;

import com.wangyz.annotation.BindView;
import com.wangyz.binder.ViewBinder;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv)
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ////调用自己定义的ViewBinder
        ViewBinder.bind(this);
        tv.setText("Hi,ViewBinder!");
    }
}
