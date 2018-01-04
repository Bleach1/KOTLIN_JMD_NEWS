package com.national.security.community;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.national.security.community.injection.component.AppComponent;
import com.national.security.community.injection.component.DaggerAppComponent;
import com.national.security.community.injection.module.AppModule;
import com.national.security.community.injection.module.HttpModule;
import com.national.security.community.service.InitializeService;

import java.util.LinkedList;

/**
 * @description: 不解释
 * @author: ljn
 * @time: 2017/12/7
 */
public class App extends Application {

    public static App instance;
    private LinkedList<Activity> activities;
    public static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        InitializeService.start(this);
    }

    public static synchronized App getInstance() {
        return instance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public void addActivity(Activity act) {
        if (activities == null) {
            activities = new LinkedList<>();
        }
        activities.add(act);
    }

    public void removeActivity(Activity act) {
        if (activities != null) {
            activities.remove(act);
        }
    }

    public void exitApp() {
        if (activities != null) {
            synchronized (activities) {
                for (Activity act : activities) {
                    act.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    public static AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(instance))
                    .httpModule(new HttpModule())
                    .build();
        }
        return appComponent;
    }
}
