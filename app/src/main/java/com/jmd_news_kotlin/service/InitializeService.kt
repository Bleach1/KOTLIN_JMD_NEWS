package com.jmd_news_kotlin.service

import android.app.IntentService
import android.content.Context
import android.content.Intent
import com.jmd_news_kotlin.utils.Constants
import com.safframework.log.L
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.Glide
import java.io.InputStream


class InitializeService : IntentService("InitializeService") {

    override fun onHandleIntent(intent: Intent?) {
        if (Constants.ACTION_INIT.equals(intent?.action)) {
            initApp()
        }
    }

    private fun initApp() {
        L.i("初始化一些第三方库...")
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, InitializeService::class.java)
            intent.action = Constants.ACTION_INIT
            context.startService(intent)
        }
    }
}