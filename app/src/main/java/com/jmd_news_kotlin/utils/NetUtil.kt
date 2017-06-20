package com.jmd_news_kotlin.utils

import android.content.Context
import android.net.ConnectivityManager
import com.jmd_news_kotlin.App

object NetUtil {

    val isNetworkConnected: Boolean
        get() {
            val connectivityManager = App.instance?.applicationContext?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return connectivityManager.activeNetworkInfo.isConnected
        }

}