package com.wangyz.breakpad.crash;

import android.content.Context;

import com.wangyz.breakpadlib.NativeCrash;

import java.io.File;

public class CrashUtil {

    //Java层Crash信息保存目录
    public static final String JAVA_CRASH_DIR = "java_crash";

    //Native层Crash信息保存目录
    public static final String NATIVE_CRASH_DIR = "native_crash";

    public static void init(Context context) {

        //初始化Java层CrashHandler
        Context applicationContext = context.getApplicationContext();
        CrashHandler.init(applicationContext, JAVA_CRASH_DIR);

        //初始化Native层CrashHandler
        File file = new File(context.getExternalCacheDir(), NATIVE_CRASH_DIR);
        if (!file.exists()) {
            file.mkdirs();
        }
        NativeCrash.init(file.getAbsolutePath());

    }

}
