package com.jmd_news_kotlin.utils.glide

import android.content.Context

import com.bumptech.glide.load.data.DataFetcher
import com.bumptech.glide.load.model.GenericLoaderFactory
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.ModelLoader
import com.bumptech.glide.load.model.ModelLoaderFactory

import java.io.InputStream

import okhttp3.OkHttpClient

class OkHttpUrlLoader(private val client: OkHttpClient) : ModelLoader<GlideUrl, InputStream> {
    /**
     * The default factory for [OkHttpUrlLoader]s.
     */
    class Factory
    /**
     * Constructor for a new Factory that runs requests using given client.
     */
    //JvmOverloads 可以使用默认参数
    @JvmOverloads constructor(private val client: OkHttpClient = OkHttpUrlLoader.Factory.internalClient as OkHttpClient) : ModelLoaderFactory<GlideUrl, InputStream> {

        override fun build(context: Context, factories: GenericLoaderFactory): ModelLoader<GlideUrl, InputStream> {
            return OkHttpUrlLoader(client)
        }

        override fun teardown() {
            // Do nothing, this instance doesn't own the client.
        }

        companion object {

            @Volatile var internalClient: OkHttpClient? = null
                get () {
                    internalClient ?: let {
                        synchronized(Factory::class.java) {
                            internalClient ?: let {
                                internalClient = UnsafeOkHttpClient.unsafeOkHttpClient
                            }
                        }
                    }
                    return internalClient
                }
        }
    }

    /**
     * Constructor for a new Factory that runs requests using a static singleton client.
     */

    override fun getResourceFetcher(model: GlideUrl, width: Int, height: Int): DataFetcher<InputStream> {
        return OkHttpStreamFetcher(client, model)
    }
}
