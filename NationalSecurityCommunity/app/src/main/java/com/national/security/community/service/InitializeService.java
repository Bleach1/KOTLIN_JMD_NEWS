package com.national.security.community.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.facebook.stetho.Stetho;
import com.github.moduth.blockcanary.BlockCanary;
import com.national.security.community.App;
import com.national.security.community.Config;
import com.national.security.community.data.db.User;
import com.national.security.community.utils.AppBlockCanaryContext;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.smtt.sdk.QbSdk;

import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;
import io.realm.annotations.RealmModule;

/**
 * @description: 初始第三方库
 * @author: ljn
 * @time: 2017/12/7
 */
public class InitializeService extends IntentService {
    private static final String ACTION_INIT = "initApplication";

    public InitializeService() {
        super("InitializeService");
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, InitializeService.class);
        intent.setAction(ACTION_INIT);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_INIT.equals(action)) {
                initApplication();
            }
        }
    }

    private void initApplication() {
        RealmConfiguration myConfig = new RealmConfiguration.Builder()
                .name(Config.DB_NAME)
                .schemaVersion(1)
                .encryptionKey(getKey())
                .modules(new MySchemaModule())
                .migration(new MyMigration())
                .build();
        Realm.setDefaultConfiguration(myConfig);

        /*内存检测*/
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(App.instance);
        /*卡顿检测*/
        BlockCanary.install(this, new AppBlockCanaryContext()).start();

//调试库 在chrome中访问 chrome://inspect
        Stetho.initializeWithDefaults(this);

        CrashReport.initCrashReport(getApplicationContext(), "注册时申请的APPID", false);
        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
        QbSdk.setDownloadWithoutWifi(true);//非wifi条件下允许下载X5内核
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.d("app", " onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {
            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(), cb);
    }

    /**
     * 机密
     *
     * @ return
     */
    private byte[] getKey() {
        return "whyMeAndNanGuo".getBytes();
    }

    @RealmModule(classes = {User.class})
    private class MySchemaModule {
    }

    //迁移
    private class MyMigration implements RealmMigration {
        @Override
        public void migrate(@NonNull DynamicRealm realm, long oldVersion, long newVersion) {

        }
    }
}
