package com.national.security.community.injection.component;


import com.national.security.community.App;
import com.national.security.community.helper.RealmHelper;
import com.national.security.community.helper.RetrofitHelper;
import com.national.security.community.injection.module.AppModule;
import com.national.security.community.injection.module.HttpModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

    App getContext();  // 提供App的Context

    RetrofitHelper retrofitHelper();  //提供http的帮助类

    RealmHelper realmHelper();    //提供数据库帮助类
}
