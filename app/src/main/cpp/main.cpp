//
// Created by x5035 on 2018/4/11.
//
#include "com_example_myapplication_ui_MainActivity.h"
#include <string.h>

JNIEXPORT jstring JNICALL Java_com_example_myapplication_ui_main_MainActivity_getString
  (JNIEnv *env, jobject obj) {

    return env->NewStringUTF("hello");

  }
