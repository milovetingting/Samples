package com.wangyz.virtualapk.host;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.didi.virtualapk.PluginManager;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final String PLUGIN_PACKAGE_NAME = "com.wangyz.virtualapk.plugin";
    private static final String PLUGIN_NAME = "com.wangyz.virtualapk.plugin.MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadPlugin();
    }

    private void loadPlugin() {
        try {
            String pluginPath = Environment.getExternalStorageDirectory().getAbsolutePath().concat("/Plugin.apk");
            File plugin = new File(pluginPath);
            PluginManager.getInstance(this).loadPlugin(plugin);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadPlugin(View view) {
        if (PluginManager.getInstance(this).getLoadedPlugin(PLUGIN_PACKAGE_NAME) == null) {
            Toast.makeText(getApplicationContext(), "未加载插件", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(PLUGIN_PACKAGE_NAME, PLUGIN_NAME));
        startActivity(intent);
    }
}
