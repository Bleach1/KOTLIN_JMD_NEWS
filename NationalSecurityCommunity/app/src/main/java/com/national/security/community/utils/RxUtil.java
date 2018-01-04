package com.national.security.community.utils;

import android.util.Log;

import com.national.security.community.Config;
import com.national.security.community.base.BaseBean;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


public class RxUtil {

    public static <T> FlowableTransformer<BaseBean<T>, T> handleResult() {

        return upstream -> upstream.flatMap((Function<BaseBean<T>, Publisher<T>>) baseBean -> subscriber -> {
            if (baseBean.getCode() == 200) {
                subscriber.onNext(baseBean.getData());
            } else {
                subscriber.onError(new ExceptionUtil(baseBean.getMsg()));
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }


    public static <T> ObservableTransformer<BaseBean<T>, T> handleObservableResult() {
        return upstream -> upstream.flatMap(tBaseBean -> (ObservableSource<T>) observer -> {
            if (tBaseBean.getCode() == 1) {
                observer.onNext(tBaseBean.getData());
            } else {
                observer.onError(new ExceptionUtil(tBaseBean.getMsg()));
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
