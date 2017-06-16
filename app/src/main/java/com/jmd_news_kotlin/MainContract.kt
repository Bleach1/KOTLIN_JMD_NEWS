package com.jmd_news_kotlin

import com.jmd_news_kotlin.mvp.IPresenter
import com.jmd_news_kotlin.mvp.IView

interface MainContract {

    interface View : IView {
        fun setMsg()
    }

    interface Presenter : IPresenter {
        fun getMsg()
    }
}