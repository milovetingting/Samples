package com.wangyz.breakpad.crash;

import android.content.Context;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private static Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler;

    private static Context mContext;

    private static String mPath;

    public static void init(Context context, String path) {
        mContext = context.getApplicationContext();
        mPath = path;
        defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(new CrashHandler());
    }


    @Override
    public void uncaughtException(@NonNull Thread thread, @NonNull Throwable throwable) {
        File dir = new File(mContext.getExternalCacheDir(), mPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        long l = System.currentTimeMillis();
        File file = new File(dir, l + ".txt");

        try {
            PrintWriter pw = new PrintWriter(new FileWriter(file));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            pw.println("time: " + sdf.format(new Date()));
            pw.println("thread: " + thread.getName());
            throwable.printStackTrace(pw);
            pw.flush();
            pw.close();
        } catch (Exception ioException) {
            ioException.printStackTrace();
        } finally {
            if (defaultUncaughtExceptionHandler != null) {
                defaultUncaughtExceptionHandler.uncaughtException(thread, throwable);
            }
        }
    }
}
