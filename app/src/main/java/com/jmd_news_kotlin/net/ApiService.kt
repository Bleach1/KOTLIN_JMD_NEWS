package com.jmd_news_kotlin.net

import com.jmd_news_kotlin.base.BaseBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by asus on 2017/6/3.
 */
interface ApiService {
    @GET("news/before/{date}")
    fun getDailyBeforeList(@Path("date") date: String): Observable<BaseBean<*>>
}