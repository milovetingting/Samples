package com.wangyz.aspectjdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wangyz.library.AddLog;
import com.wangyz.library.ExecTime;

/**
 * @author wangyz
 * @time 2020/1/13 13:10
 * @description MainActivity
 */
public class MainActivity extends Activity implements View.OnClickListener {

    @AddLog
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_open).setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @ExecTime
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_open:
                Intent intent = new Intent(this, SecondActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
