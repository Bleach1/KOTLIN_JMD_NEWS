package com.national.security.community.data;


import com.national.security.community.base.BaseBean;
import com.national.security.community.data.model.HomeBean;
import com.national.security.community.data.model.TestBean;

import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * @ description:  具体请求
 * @ author:  ljn
 * @ time:  2018/1/2
 */

public interface ApiService {

    @GET("news/before/{date}")
    Flowable<BaseBean<TestBean>> getDailyBeforeList2(@Path("date") String data);

    @GET("index")
    Observable<BaseBean<HomeBean>> loadHome();


    @GET("test")
    Observable<BaseBean> test(@QueryMap Map<String, String> value);

    //动态修改地址
    @POST()
    Observable<BaseBean> post(@Url String url, @QueryMap Map<String, String> map);

    @POST("api/{url}/newsList")
    Observable<BaseBean> login(@Path("url") String url, @Body BaseBean post);


}
