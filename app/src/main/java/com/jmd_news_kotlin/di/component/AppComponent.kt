package com.jmd_news_kotlin.di.component

import com.jmd_news_kotlin.App
import com.jmd_news_kotlin.di.module.AppModule
import com.jmd_news_kotlin.di.module.HttpModule
import com.jmd_news_kotlin.net.ApiService
import com.jmd_news_kotlin.utils.RetrofitHelper
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, HttpModule::class))
interface AppComponent {

    fun getContext(): App
    fun retrofitHelper(): RetrofitHelper
    fun apiService(): ApiService
}