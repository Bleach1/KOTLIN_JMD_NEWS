package com.national.security.community;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatDelegate;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.Utils;
import com.bumptech.glide.Glide;
import com.national.security.community.injection.component.AppComponent;
import com.national.security.community.injection.component.DaggerAppComponent;
import com.national.security.community.injection.module.AppModule;
import com.national.security.community.injection.module.HttpModule;
import com.national.security.community.service.InitializeService;
import com.national.security.community.utils.DynamicTimeFormat;
import com.national.security.community.utils.network.NetStateReceiver;
import com.qihoo360.replugin.RePlugin;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import java.util.LinkedList;

import io.realm.Realm;

/**
 * @ description: 不解释
 * @ author: ljn
 * @ time: 2017/12/7
 */
@SuppressWarnings("SynchronizeOnNonFinalField")
public class App extends Application {

    public static App instance;
    private LinkedList<Activity> activities;
    public static AppComponent appComponent;

    static {
        //启用矢量图兼容
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> {
            //全局设置主题颜色
            layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);
            return new ClassicsHeader(context).setTimeFormat(new DynamicTimeFormat("更新于 %s"));
        });
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        if (instance != null) {
            NetStateReceiver.unRegisterNetworkStateReceiver(instance);
        }
        Glide.get(this).clearMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (level == TRIM_MEMORY_UI_HIDDEN) {
            Glide.get(this).clearMemory();
        }
        Glide.get(this).trimMemory(level);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Realm.init(this);
        if (BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        Utils.init(this);
        ARouter.init(this);
        RePlugin.App.onCreate();
        InitializeService.start(this);
        NetStateReceiver.registerNetworkStateReceiver(this);
    }

    public static synchronized App getInstance() {
        return instance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
        RePlugin.App.attachBaseContext(this);
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
