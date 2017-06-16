package com.jmd_news_kotlin.base

import android.app.Activity
import com.jmd_news_kotlin.mvp.IPresenter
import com.jmd_news_kotlin.mvp.IView
import com.jmd_news_kotlin.net.ApiService
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action

/**
 * Created by asus on 2017/6/3.
 */
open class BasePresenter<T : IView>(protected var activity: Activity?, protected var mView: T?) : IPresenter {


    protected var mCompositeDisposable: CompositeDisposable? = null
    protected val apiService: ApiService? = null

    override fun detachView() {
        this.mView = null
        unSubscribe()
    }

    protected fun unSubscribe() {
        mCompositeDisposable?.dispose()

    }

    protected fun addSubscribe(disposable: Disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = CompositeDisposable()
        }
        mCompositeDisposable?.add(disposable)
    }

    protected fun <U> addRxBusSubscribe(eventType: Class<U>) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = CompositeDisposable()
        }
        mCompositeDisposable?.add(RxBus.toObservable(eventType).compose(RxUtil.rxSchedulerHelper()).subscribe())
    }

}