package com.jmd_news_kotlin.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jmd_news_kotlin.App
import com.jmd_news_kotlin.di.component.DaggerFragmentComponent
import com.jmd_news_kotlin.di.component.FragmentComponent
import com.jmd_news_kotlin.di.module.FragmentModule
import com.jmd_news_kotlin.mvp.IPresenter
import com.jmd_news_kotlin.mvp.IView
import me.yokeyword.fragmentation.SupportFragment
import javax.inject.Inject

abstract class BaseFragment<T : IPresenter> : SupportFragment(), IView {

    @Inject
    @JvmField
    protected var mPresenter: T? = null
    protected var mActivity: Activity? = null
    protected var mContext: Context? = null
    protected var isInited = false
    protected var mView: View? = null
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mActivity = context as Activity
        mContext = context
    }

    protected fun getFragmentComponent(): FragmentComponent {
        return DaggerFragmentComponent.builder()
                .appComponent(App.appComponent)
                .fragmentModule(FragmentModule(this))
                .build()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater?.inflate(getLayoutId(), null)
        initInject()
        return mView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            if (!isHidden) {
                isInited = true
                initEventAndData()
            }
        } else {
            if (isSupportVisible) {
                isInited = true
                initEventAndData()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.detachView()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!isInited && !hidden) {
            isInited = true
            initEventAndData()
        }
    }

    protected abstract fun initInject()
    protected abstract fun getLayoutId(): Int
    protected abstract fun initEventAndData()
}