package com.national.security.community.architecture_components;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.util.Log;

/**
 * @description: 绑定生命周期
 * LifecycleOwner:生命周期的事件分发者，在 Activity/Fragment 他们的生命周期发生变化的时候
 * 发出相应的 Event 给LifecycleRegistry。
 * LifecycleObserver:生命周期的观察者，通过注解将处理函数与希望监听的Event绑定,当相应的Event发生时,
 * LifecycleRegistry会通知相应的函数进行处理
 * LifecycleRegistry:控制中心。它负责控制state的转换、接受分发event事件
 * @author: ljn
 * @time: 2018/6/29
 */
public class LifeListener implements LifecycleObserver {

    private static final String TAG = "ljn";

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate(LifecycleOwner owner) {
        Log.i(TAG, "onCreate: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void onStart(LifecycleOwner owner) {
        Log.i(TAG, "onStart: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void onResume(LifecycleOwner owner) {
        Log.i(TAG, "onResume: ");
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void onPause(LifecycleOwner owner) {
        Log.i(TAG, "onPause: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void onStop(LifecycleOwner owner) {
        Log.i(TAG, "onStop: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy(LifecycleOwner owner) {
        Log.i(TAG, "onDestroy: ");
    }
}
