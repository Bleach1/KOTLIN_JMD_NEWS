package com.national.security.community.injection.module;

import com.national.security.community.injection.scope.ActivityScope;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import dagger.Module;
import dagger.Provides;


@Module
public class ActivityModule {
    private RxAppCompatActivity mActivity;

    public ActivityModule(RxAppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityScope
    RxAppCompatActivity provideActivity() {
        return mActivity;
    }
}
