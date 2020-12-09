package com.wangyz.crash;

public class CrashTest {

    static {
        System.loadLibrary("crash_test");
    }

    public static void testCrash() {
        test();
    }

    private static native void test();
}
