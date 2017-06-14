package com.jmd_news_kotlin.utils

import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.processors.FlowableProcessor
import io.reactivex.processors.PublishProcessor
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

/**
 * Created by asus on 2017/6/3.
 */
object RxBus {

    private val bus: Subject<Any>
    private val mBus: FlowableProcessor<Any>

    init {
        bus = PublishSubject.create()
        mBus = PublishProcessor.create()
    }

    fun post(o: Any) {
        bus.onNext(o)
    }

    fun post_B(o: Any) {
        mBus.onNext(o)
    }

    fun <T> toObservable(clazz: Class<T>): Observable<T> {
        return bus.ofType(clazz);
    }

    fun toObservable(): Observable<Any> {
        return bus
    }

    fun <T> toFlowable(clazz: Class<T>): Flowable<T> {
        return mBus.ofType(clazz);
    }

    fun toFlowable(): Flowable<Any> {
        return mBus
    }


    fun hasObservers(): Boolean {
        return bus.hasObservers()
    }

    fun hasSubscribers(): Boolean {
        return mBus.hasSubscribers()
    }
}