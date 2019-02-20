package com.wangyz.dynamicicon;

import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void changeIcon(View view) {
        getPackageManager().setComponentEnabledSetting(getComponentName(),
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
        getPackageManager().setComponentEnabledSetting(new ComponentName(this,
                        getPackageName() + ".MainActivity"),
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                0);
    }

    public void changeIconAlias(View view) {
        getPackageManager().setComponentEnabledSetting(getComponentName(),
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
        getPackageManager().setComponentEnabledSetting(new ComponentName(this,
                        getPackageName() + ".alias"),
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                0);
    }
}
