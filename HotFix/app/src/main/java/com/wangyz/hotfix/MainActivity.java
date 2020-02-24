package com.wangyz.hotfix;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Foo foo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        foo = new Foo();
        foo.showToastShort(getApplicationContext(), "BUG修复啦~~~");
    }
}
