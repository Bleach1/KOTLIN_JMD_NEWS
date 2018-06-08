package com.national.security.community.helper;

import com.national.security.community.base.BaseBean;
import com.national.security.community.data.ApiService;
import com.national.security.community.data.CacheProviders;
import com.national.security.community.data.model.HomeBean;
import com.national.security.community.data.model.TestBean;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;
import io.rx_cache2.Reply;

/**
 * @ description:  Retrofit帮助类
 * @ author:  ljn
 * @ time:  2018/1/2
 */
public class RetrofitHelper {
    private ApiService apiService;
    private CacheProviders cacheProviders;

    public RetrofitHelper(ApiService apiService) {
        this.apiService = apiService;
    }

    public RetrofitHelper(ApiService apiService, CacheProviders cacheProviders) {
        this.apiService = apiService;
        this.cacheProviders = cacheProviders;
    }


    public Flowable<BaseBean<TestBean>> fetchDailyListInfo2() {
        return apiService.getDailyBeforeList2("");
    }

    public Flowable<Reply<BaseBean<TestBean>>> cacheVersionInfo(String cache_info, boolean update) {
        return cacheProviders.getVersionInfo(fetchDailyListInfo2(), new DynamicKey(cache_info), new EvictDynamicKey(update));
    }

    public Observable<BaseBean<HomeBean>> loadHome() {
        return apiService.loadHome();
    }
}
