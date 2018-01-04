package com.national.security.community.data;


import com.national.security.community.base.BaseBean;
import com.national.security.community.data.model.HomeBean;
import com.national.security.community.data.model.TestBean;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
/**
 @ description:  具体请求
 @ author:  ljn
 @ time:  2018/1/2
 */

public interface ApiService {

    String HOST = "http://47.93.29.96/weijinrong/app/";

    @GET("news/before/{date}")
    Flowable<BaseBean<TestBean>> getDailyBeforeList2(@Path("date") String data);

    @GET("index")
    Observable<BaseBean<HomeBean>> loadHome();
}
