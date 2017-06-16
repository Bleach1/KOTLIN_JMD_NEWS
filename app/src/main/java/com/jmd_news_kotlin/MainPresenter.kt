package com.jmd_news_kotlin

import android.app.Activity
import com.jmd_news_kotlin.base.BasePresenter

class MainPresenter(activity: Activity?, mView: MainContract.View?) : BasePresenter<MainContract.View>(activity, mView),
        MainContract.Presenter {
    override fun getMsg() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

    }


}