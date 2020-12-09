# CMake方式需要在build.grale中配置其它信息,但可以build生成aar
# 采用Android.mk配置，不需要再额外配置信息

cmake_minimum_required(VERSION 3.4.1)

include_directories(cpp/)

add_library(crash_test SHARED cpp/crash_test.cpp)

target_link_libraries(crash_test)