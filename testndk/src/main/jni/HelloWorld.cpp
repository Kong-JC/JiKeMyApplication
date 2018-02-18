//
// Created by Kong on 2018/2/12.
//

#include "com_example_testndk_HelloWorld.h"

jstring Java_com_example_testndk_HelloWorld_hello(JNIEnv *env, jobject){
    return env->NewStringUTF("Hello NDK!");
}