package com.national.security.community.utils;

public class JNIUtil {

    static {
        System.loadLibrary("native-lib");
    }

    public static native int bspatch(String oldApk, String newApk, String patch);

    //java  调用C
    public native static String show();
    //C调用java  反射
    /*
    1.得到字节码
      jclass jclazz=(*env)->FindClass(env,"com/national/security/community/utils/JNIUtil");
    2.得到方法
      app->build->intermediates->classes->debug  javap  -s  packageName+className
     jmethodID jmethodIDs=(*env)->GetMethodID(env,jclazz,"methodName","methodSign");
    3.实例化该类
     jobject  jobject=(*env)->AllocObject(env,jclazz);
    4.调用方法
     (*env)->CallIntMethod(env,jobject,jmethodIDs,99,1);
    */
    //C调用java静态方法
   /*
     jclass jclazz=(*env)->FindClass(env,"com/national/security/community/utils/JNIUtil");
     jmethodID jmethodIDs=(*env)->GetStaticMethodID(env,jclazz,"methodName","methodSign");
     (*env)->CallStaticIntMethod(env,jobject,jmethodIDs,99,1);
   */
}
