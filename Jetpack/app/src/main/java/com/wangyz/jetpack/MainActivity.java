package com.wangyz.jetpack;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.wangyz.jetpack.databinding.DataBindingActivity;
import com.wangyz.jetpack.lifecycle.LifeCycleActivity;
import com.wangyz.jetpack.livedata.LiveDataActivity;
import com.wangyz.jetpack.navigation.NavigationActivity;
import com.wangyz.jetpack.paging.PagingActivity;
import com.wangyz.jetpack.room.RoomActivity;
import com.wangyz.jetpack.workmanager.WorkManagerActivity;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "Jetpack";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openLifeCycle(View view) {
        Intent intent = new Intent(this, LifeCycleActivity.class);
        startActivity(intent);
    }

    public void openLiveData(View view) {
        Intent intent = new Intent(this, LiveDataActivity.class);
        startActivity(intent);
    }

    public void openDataBinding(View view) {
        Intent intent = new Intent(this, DataBindingActivity.class);
        startActivity(intent);
    }

    public void openRoom(View view) {
        Intent intent = new Intent(this, RoomActivity.class);
        startActivity(intent);
    }

    public void openNavigation(View view) {
        Intent intent = new Intent(this, NavigationActivity.class);
        startActivity(intent);
    }

    public void openPaging(View view) {
        Intent intent = new Intent(this, PagingActivity.class);
        startActivity(intent);
    }

    public void openWorkManager(View view) {
        Intent intent = new Intent(this, WorkManagerActivity.class);
        startActivity(intent);
    }
}
