#include <jni.h>
#include <string>
#include <android/log.h>

#define  TAG "ljn"
// 定义info信息
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO,TAG,__VA_ARGS__)
// 定义debug信息
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, TAG, __VA_ARGS__)
// 定义error信息
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR,TAG,__VA_ARGS__)

extern "C"
JNIEXPORT jstring
JNICALL
Java_com_national_security_community_utils_JNIUtil_show(JNIEnv *env, jclass type) {

    // TODO
    std::string hello = "Hello from C++";
    LOGI(TAG, hello.c_str());
    LOGD(TAG, hello.c_str());
    LOGE(TAG, hello.c_str());
    return env->NewStringUTF(hello.c_str());
}