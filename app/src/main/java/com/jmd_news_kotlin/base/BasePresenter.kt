package com.jmd_news_kotlin.base

import android.app.Activity
import com.jmd_news_kotlin.mvp.IPresenter
import com.jmd_news_kotlin.mvp.IView
import com.jmd_news_kotlin.net.ApiService
import rx.Subscription
import rx.subscriptions.CompositeSubscription

/**
 * Created by asus on 2017/6/3.
 */
open class BasePresenter<T : IView>(protected var activity: Activity?, protected var mView: T?) : IPresenter {


    protected var mCompositeSubscription: CompositeSubscription? = null
    protected val apiService: ApiService? = null

    override fun detachView() {
        this.mView = null
        unSubscribe()
    }

    protected fun unSubscribe() {

        if (mCompositeSubscription != null) {
            mCompositeSubscription!!.unsubscribe()
        }
    }

    protected fun addSubscribe(subscription: Subscription) {

        if (mCompositeSubscription == null) {
            mCompositeSubscription = CompositeSubscription()
        }
        mCompositeSubscription!!.add(subscription)
    }
}