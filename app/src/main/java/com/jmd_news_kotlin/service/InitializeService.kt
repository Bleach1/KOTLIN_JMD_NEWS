package com.jmd_news_kotlin.service

import android.app.IntentService
import android.content.Context
import android.content.Intent
import com.jmd_news_kotlin.App
import com.jmd_news_kotlin.utils.Constants
import com.safframework.log.L
import com.zxy.tiny.Tiny


class InitializeService : IntentService("InitializeService") {

    override fun onHandleIntent(intent: Intent?) {
        if (Constants.ACTION_INIT == intent?.action) {
            initApp()
        }
    }

    private fun initApp() {
        L.i("初始化一些第三方库...")
        Tiny.getInstance().init(App.instance)
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, InitializeService::class.java)
            intent.action = Constants.ACTION_INIT
            context.startService(intent)
        }
    }
}