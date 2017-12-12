package com.jmd_news_kotlin.base

import RxBus
import RxUtil
import com.jmd_news_kotlin.mvp.IPresenter
import com.jmd_news_kotlin.mvp.IView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by asus on 2017/6/3.
 */
open class BasePresenter<T : IView> : IPresenter {


    protected var mCompositeDisposable: CompositeDisposable? = null
    protected var mView: T? = null
    override fun detachView() {
        this.mView = null
        unSubscribe()
    }

    protected fun unSubscribe() {
        mCompositeDisposable?.clear()

    }

    protected fun addSubscribe(disposable: Disposable) {
        mCompositeDisposable ?: let { mCompositeDisposable = CompositeDisposable() }
        mCompositeDisposable?.add(disposable)
    }

    protected fun <U> addRxBusSubscribe(eventType: Class<U>) {
        mCompositeDisposable ?: let { mCompositeDisposable = CompositeDisposable() }
        mCompositeDisposable?.add(RxBus.toObservable(eventType).compose(RxUtil.rxSchedulerHelper()).subscribe())
    }

}