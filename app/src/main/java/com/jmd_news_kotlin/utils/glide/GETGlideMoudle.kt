package com.jmd_news_kotlin.utils.glide

import android.content.Context

import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.GlideModule

import java.io.InputStream

class GETGlideMoudle : GlideModule {


    override fun applyOptions(context: Context, glideBuilder: GlideBuilder) {

    }

    override fun registerComponents(context: Context, glide: Glide) {
        glide.register(GlideUrl::class.java, InputStream::class.java, OkHttpUrlLoader2.Factory())
    }
}
