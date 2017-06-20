package com.jmd_news_kotlin.di.component

import android.app.Activity
import com.jmd_news_kotlin.MainActivity
import com.jmd_news_kotlin.di.module.ActivityModule
import com.jmd_news_kotlin.di.scope.ActivityScope
import dagger.Component

@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun getActivity(): Activity
    fun inject(mainActivity: MainActivity)
}