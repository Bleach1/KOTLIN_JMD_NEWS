package com.jmd_news_kotlin.di.component

import android.app.Activity
import android.support.v4.app.Fragment
import com.jmd_news_kotlin.di.module.FragmentModule
import com.jmd_news_kotlin.di.scope.FragmentScope
import dagger.Component

@FragmentScope
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(FragmentModule::class))
interface FragmentComponent {
    fun getActivity(): Activity
    fun inject(fragment: Fragment)
}