cmake_minimum_required(VERSION 3.4.1)

include_directories(breakpad/src breakpad/src/common/android/include)

add_library(breakpad_lib SHARED breakpad_lib.cpp)

add_subdirectory(breakpad)

target_link_libraries(breakpad_lib breakpad log)