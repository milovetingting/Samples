package com.wangyz.multichannel;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView mChannel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mChannel = findViewById(R.id.tv_channel_value);
        String channel = getMetaData(getApplicationContext(), "APP_CHANNEL");
        mChannel.setText(channel);
        Toast.makeText(getApplicationContext(), BuildConfig.FIELD_TEST, Toast.LENGTH_LONG).show();
    }

    private String getMetaData(Context context, String key) {
        try {
            PackageManager packageManager = context.getPackageManager();
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context
                    .getPackageName(), PackageManager.GET_META_DATA);
            return applicationInfo.metaData.getString(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
