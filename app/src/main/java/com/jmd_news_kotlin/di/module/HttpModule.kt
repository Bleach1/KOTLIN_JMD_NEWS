package com.jmd_news_kotlin.di.module

import com.google.gson.GsonBuilder
import com.jmd_news_kotlin.App
import com.jmd_news_kotlin.BuildConfig
import com.jmd_news_kotlin.net.CacheProviders
import com.jmd_news_kotlin.utils.AppManager
import com.jmd_news_kotlin.utils.Constants
import com.safframework.log.L
import dagger.Module
import dagger.Provides
import io.rx_cache2.internal.RxCache
import io.victoralbertos.jolyglot.GsonSpeaker
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class HttpModule {


    @Singleton
    @Provides
    fun provideRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
    }

    @Singleton
    @Provides
    fun provideOkHttpBuilder(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
    }

    @Singleton
    @Provides
    fun providerRetrofit(builder: Retrofit.Builder, client: OkHttpClient): Retrofit {
        return creatRetrofit(builder, client, Constants.BASEURL)
    }

    @Singleton
    @Provides
    fun provideRxCache(): CacheProviders {
        return RxCache.Builder().persistence(App.instance?.filesDir, GsonSpeaker()).using(CacheProviders::class.java)
    }

    @Singleton
    @Provides
    fun providerClient(builder: OkHttpClient.Builder): OkHttpClient {
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
            builder.addInterceptor(loggingInterceptor)
        }

        val cacheFile = File(Constants.PATH_CACHE)
        val cache = Cache(cacheFile, 1024 * 1024 * 50)
        //cache拦截器
        val cacheInterceptor = Interceptor { chain ->
            var request = chain.request()
            if (!AppManager.isNetworkConnected) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build()
            }
            val response = chain.proceed(request)
            if (AppManager.isNetworkConnected) {
                val maxAge = 0
                // 有网络时, 不缓存, 最大保存时长为0
                response.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .removeHeader("Pragma")
                        .build()
            } else {
                // 无网络时，设置超时为4周
                val maxStale = 60 * 60 * 24 * 28
                response.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .removeHeader("Pragma")
                        .build()
            }

            response
        }

        val apikey = Interceptor { chain ->
            var request = chain.request()
            //http://blog.csdn.net/spinchao/article/details/52932145
            val params = HashMap<String, String>()
            if (request.method() == "POST") {

                if (request.body() is FormBody) {
                    val bodyBuilder = FormBody.Builder()
                    var formBody = request.body() as FormBody
                    //把原来的参数添加到新的构造器，（因为没找到直接添加，所以就new新的）
                    for (i in 0..formBody.size() - 1) {
                        bodyBuilder.addEncoded(formBody.encodedName(i), formBody.encodedValue(i))
                        //拿到请求的参数
                        L.i(formBody.name(i) + formBody.value(i))
                        params.put(formBody.encodedName(i), formBody.encodedValue(i))
                    }
                    if (!params.containsKey("v")) {
                        params.put("v", "1.0")
                        bodyBuilder.addEncoded("v", "1.0")
                    }
                    params.put("appKey", "1")
                    params.put("method", "imei")
                    params.put("format", "VersionName")
                    params.put("timestamp", System.currentTimeMillis().toString())
                    params.put("deviceId", "imei")

                    formBody = bodyBuilder
                            .addEncoded("appKey", "1")
                            .addEncoded("method", "imei")
                            .addEncoded("format", "VersionName")
                            .addEncoded("timestamp", System.currentTimeMillis().toString())
                            .addEncoded("deviceId", "imei")
                            .addEncoded("sign", "imei")
                            .build()

                    request = request.newBuilder().post(formBody).build()
                }
            }
            chain.proceed(request)
        }
        //设置统一的请求头部参数
        builder.addInterceptor(apikey)
        //设置缓存
        builder.addNetworkInterceptor(cacheInterceptor)
        builder.addInterceptor(cacheInterceptor)
        builder.cache(cache)
        //设置超时
        builder.connectTimeout(10, TimeUnit.SECONDS)
        builder.readTimeout(20, TimeUnit.SECONDS)
        builder.writeTimeout(20, TimeUnit.SECONDS)
        //错误重连
        builder.retryOnConnectionFailure(true)
        return builder.build()
    }


    private fun creatRetrofit(builder: Retrofit.Builder, client: OkHttpClient, url: String): Retrofit {
        return builder.baseUrl(url).client(client).addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder()
                        .disableHtmlEscaping()
                        .setPrettyPrinting()
                        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                        .create()))
                .build()
    }
}