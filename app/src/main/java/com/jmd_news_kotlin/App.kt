package com.jmd_news_kotlin

import android.app.Activity
import android.content.Context
import android.support.multidex.MultiDexApplication
import com.jmd_news_kotlin.di.component.AppComponent
import com.jmd_news_kotlin.di.component.DaggerAppComponent
import com.jmd_news_kotlin.di.module.AppModule
import com.jmd_news_kotlin.di.module.HttpModule
import java.util.*

/**
 * Created by asus on 2017/6/3.
 */
class App : MultiDexApplication() {
    private var activityList = LinkedList<Activity>()
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        instance = this
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(instance as App))
                .httpModule(HttpModule())
                .build()
    }

    companion object {
        var context: Context? = null
        var instance: App? = null
        var appComponent: AppComponent? = null
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