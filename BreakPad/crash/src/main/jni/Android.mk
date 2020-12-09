LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE := crash_test
LOCAL_SRC_FILES := cpp/crash_test.cpp
include $(BUILD_SHARED_LIBRARY)