package com.national.security.community.data;


import com.national.security.community.base.BaseBean;
import com.national.security.community.data.model.TestBean;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;
import io.rx_cache2.LifeCache;
import io.rx_cache2.Reply;
/**
 @ description:  Rxcache 缓存接口
 @ author:  ljn
 @ time:  2018/1/2
 */

public interface CacheProviders {

    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)
    Flowable<Reply<BaseBean<TestBean>>> getfetchVersionInfo(Flowable<BaseBean<TestBean>> fetchVersionInfo, DynamicKey userName, EvictDynamicKey evictDynamicKey);
}
