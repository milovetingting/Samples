package com.wangyz.jetpack.workmanager;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.wangyz.jetpack.MainActivity;

/**
 * @author wangyz
 * @time 2020/4/20 8:48
 * @description UploadWorker
 */
public class UploadWorker extends Worker {

    public UploadWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.i(MainActivity.TAG, "doWork");
        return Result.success();
    }
}
