package com.wangyz.breakpadlib;

public class NativeCrash {

    static {
        System.loadLibrary("breakpad_lib");
    }

    public static void init(String path) {
        initNativeCrash(path);
    }

    /**
     * 初始化NativeCrash
     *
     * @param path
     */
    private static native void initNativeCrash(String path);

}
