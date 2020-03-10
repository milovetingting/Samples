package com.wangyz.plugindemo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openPlugin(View view) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.wangyz.plugin", "com.wangyz.plugin.MainActivity"));
        startActivity(intent);
    }
}
