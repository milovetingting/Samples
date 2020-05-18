package com.wangyz.customview;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.wangyz.customview.custom.CustomFlowLayout;

import java.util.Random;

/**
 * @author wangyz
 * @time 2020/5/14 14:53
 * @description FlowLayoutActivity
 */
public class FlowLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_layout);
        addViews();
    }

    private void addViews() {
        CustomFlowLayout flowLayout = findViewById(R.id.flow_layout);
        Random random = new Random();
        for (int i = 0; i < 200; i++) {
            TextView view = new TextView(this);
            view.setText("item" + i);
            view.setBackgroundColor(Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
            view.setGravity(Gravity.CENTER);
            ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.width = 200 + random.nextInt(3) * 50;
            lp.height = 100;
            lp.setMargins(20, 20, 20, 20);
            view.setLayoutParams(lp);
            flowLayout.addView(view);
        }
    }
}
