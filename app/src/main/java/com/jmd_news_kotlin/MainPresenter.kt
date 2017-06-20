package com.jmd_news_kotlin

import com.jmd_news_kotlin.base.BasePresenter
import com.jmd_news_kotlin.utils.RetrofitHelper
import com.safframework.log.L
import javax.inject.Inject

class MainPresenter @Inject constructor(mRetrofitHelper: RetrofitHelper) : BasePresenter<MainContract.View>(),
        MainContract.Presenter {
    private var retrofitHelper: RetrofitHelper = mRetrofitHelper
    override fun getMsg() {
        L.i("*******************************************************")
    }


}