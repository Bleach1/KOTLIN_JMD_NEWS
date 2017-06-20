package com.jmd_news_kotlin.base

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.SupportActivity
import com.jmd_news_kotlin.App
import com.jmd_news_kotlin.di.component.ActivityComponent
import com.jmd_news_kotlin.di.component.DaggerActivityComponent
import com.jmd_news_kotlin.di.module.ActivityModule
import com.jmd_news_kotlin.mvp.IPresenter
import com.jmd_news_kotlin.mvp.IView
import javax.inject.Inject

/**
 * Created by asus on 2017/6/3.
 */
abstract class BaseActivity<T : IPresenter> : SupportActivity(), IView {
    //Kotlin会生成私有的
    @Inject
    @JvmField
    protected var mPresenter: T? = null
    protected var activity: Activity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        activity = this
        App.instance?.addActivity(this)
        initInject()
        initEventAndData()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.detachView()
        App.instance?.removeActivity(this)
    }

    protected abstract fun initEventAndData()

    protected abstract fun initInject()

    protected abstract fun getLayout(): Int

    protected fun getActivityComponent(): ActivityComponent {
        return DaggerActivityComponent.builder()
                .appComponent(App.appComponent)
                .activityModule(ActivityModule(this))
                .build()
    }
}