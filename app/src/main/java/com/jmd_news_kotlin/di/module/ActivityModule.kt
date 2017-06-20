package com.jmd_news_kotlin.di.module

import android.app.Activity
import com.jmd_news_kotlin.di.scope.ActivityScope
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module
class ActivityModule @Inject constructor(activity: Activity) {

    private var mActivity: Activity = activity

    @Provides
    @ActivityScope
    fun providerActivity(): Activity {
        return mActivity
    }
}