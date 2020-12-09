package com.wangyz.breakpad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.wangyz.breakpadlib.NativeCrash;
import com.wangyz.crash.CrashTest;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //模拟java crash
        //int i = 1 / 0;
        //模拟native crash
        CrashTest.testCrash();
    }
}