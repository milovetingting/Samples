package com.wangyz.jetpack.workmanager;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.wangyz.jetpack.R;

/**
 * @author wangyz
 * @time 2020/4/20 8:45
 * @description WorkManagerActivity
 */
public class WorkManagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workmanager);

        OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(UploadWorker.class).build();
        WorkManager.getInstance(getApplicationContext()).enqueue(request);
    }
}
