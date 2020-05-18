package com.wangyz.customview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author wangyz
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openViewGroup(View view) {
        Intent intent = new Intent(this, ViewGroupActivity.class);
        startActivity(intent);
    }

    public void openFlowLayout(View view) {
        Intent intent = new Intent(this, FlowLayoutActivity.class);
        startActivity(intent);
    }

    public void openViewPager(View view) {
        Intent intent = new Intent(this, ViewPagerActivity.class);
        startActivity(intent);
    }
}
