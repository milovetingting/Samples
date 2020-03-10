package com.wangyz.plugin;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public Resources getResources() {
        Resources resources = ResourceManager.getResources(getApplicationContext());
        return resources == null ? super.getResources() : resources;
    }
}
