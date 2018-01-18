package com.example.administrator.pluginseries;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.example.administrator.pluginseries.ui.ProxyActivity;
import com.example.administrator.pluginseries.utils.HookUtil;

public class App extends Application {
    @SuppressLint("StaticFieldLeak")
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        HookUtil hookUtil = new HookUtil(ProxyActivity.class, this);
        hookUtil.hookSystemHandler();
        hookUtil.hookAms();
    }
}
