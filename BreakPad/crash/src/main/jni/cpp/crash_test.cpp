#include <jni.h>

extern "C"
JNIEXPORT void JNICALL
Java_com_wangyz_crash_CrashTest_test(JNIEnv* env,jclass clazz) {
    int * i = NULL;
    *i = 0;
}