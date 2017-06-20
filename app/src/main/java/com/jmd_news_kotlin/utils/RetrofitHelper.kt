package com.jmd_news_kotlin.utils

import com.jmd_news_kotlin.base.BaseBean
import com.jmd_news_kotlin.net.ApiService
import io.reactivex.Observable

class RetrofitHelper constructor(apiService: ApiService) {
    private var mApiService: ApiService = apiService
    fun fetchDailyListInfo(): Observable<BaseBean<*>> {
        return mApiService.getDailyBeforeList("")
    }
}