package com.jmd_news_kotlin

import android.app.Activity
import android.content.Context
import android.support.multidex.MultiDexApplication

/**
 * Created by asus on 2017/6/3.
 */
class App : MultiDexApplication() {
    private var activityList = mutableSetOf<Activity>()
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        instance = this
    }

    companion object {
        var context: Context? = null
        var instance: App? = null
    }

    fun addActivity(act: Activity) {
        activityList.add(act)
    }

    fun removeActivity(act: Activity) {
        activityList.remove(act)
    }

    fun exitApp() {
        synchronized(activityList) {
            activityList.forEach { act -> act.finish() }
        }
        android.os.Process.killProcess(android.os.Process.myPid())
        System.exit(0)
    }
}