#include <jni.h>
#include <android/log.h>
#include "client/linux/handler/minidump_descriptor.h"
#include "client/linux/handler/exception_handler.h"

#define TAG "breakpad_lib"

bool DumpCallback(const google_breakpad::MinidumpDescriptor &descriptor,
                  void *context,
                  bool succeeded) {
    __android_log_print(ANDROID_LOG_ERROR, TAG, "native crash:%s", descriptor.path());
    return false;
}

extern "C"
JNIEXPORT void JNICALL
Java_com_wangyz_breakpadlib_NativeCrash_initNativeCrash(JNIEnv *env, jclass clazz, jstring path_) {

    const char *path = env->GetStringUTFChars(path_, 0);

    __android_log_print(ANDROID_LOG_INFO, TAG, "initNativeCrash,dmp path:%s", path);

    google_breakpad::MinidumpDescriptor descriptor(path);

    static google_breakpad::ExceptionHandler eh(descriptor, NULL, DumpCallback, NULL, true, -1);

    env->ReleaseStringUTFChars(path_, path);

}