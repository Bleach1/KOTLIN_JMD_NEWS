package com.national.security.community.utils.glide;

import java.net.SocketTimeoutException;

import io.reactivex.subscribers.DisposableSubscriber;
import retrofit2.HttpException;


public abstract class MyFlowable<T> extends DisposableSubscriber<T> {


    public MyFlowable() {
        super();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onNext(T t) {
        _onNext(t);
    }

    @Override
    public void onError(Throwable e) {
        String msg = "";
        if (e instanceof SocketTimeoutException) {
            msg = "请求超时";
        } else if (e instanceof HttpException) {
            msg = "服务器异常 ";
        }
        _onError(msg);
    }

    @Override
    public void onComplete() {

    }

    protected abstract void _onNext(T t);

    protected abstract void _onError(String msg);
}
