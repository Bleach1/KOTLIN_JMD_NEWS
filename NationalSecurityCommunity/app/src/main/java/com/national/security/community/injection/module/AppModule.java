package com.national.security.community.injection.module;


import com.national.security.community.App;
import com.national.security.community.data.ApiService;
import com.national.security.community.helper.RealmHelper;
import com.national.security.community.helper.RetrofitHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class AppModule {
    private final App application;

    public AppModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    App provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    RetrofitHelper provideRetrofitHelper(ApiService apiService) {
        return new RetrofitHelper(apiService);
    }

    @Provides
    @Singleton
    RealmHelper provideRealmHelper() {
        return new RealmHelper(application);
    }
}
