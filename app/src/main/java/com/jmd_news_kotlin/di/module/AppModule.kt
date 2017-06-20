package com.jmd_news_kotlin.di.module

import com.jmd_news_kotlin.App
import com.jmd_news_kotlin.net.ApiService
import com.jmd_news_kotlin.utils.RetrofitHelper
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class AppModule constructor(app: App) {

    private var application: App = app

    @Provides
    @Singleton
    fun provideApplicationContext(): App {
        return application
    }

    @Provides
    @Singleton
    fun providerApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofitHelper(apiService: ApiService): RetrofitHelper {
        return RetrofitHelper(apiService)
    }
}