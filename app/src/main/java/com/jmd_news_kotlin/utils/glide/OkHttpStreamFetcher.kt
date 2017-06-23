package com.jmd_news_kotlin.utils.glide

import com.bumptech.glide.Priority
import com.bumptech.glide.load.data.DataFetcher
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.util.ContentLengthInputStream
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.ResponseBody
import java.io.IOException
import java.io.InputStream

class OkHttpStreamFetcher(private val client: OkHttpClient, private val url: GlideUrl) : DataFetcher<InputStream> {
    private var stream: InputStream? = null
    private var responseBody: ResponseBody? = null

    @Throws(Exception::class)
    override fun loadData(priority: Priority): InputStream {
        val requestBuilder = Request.Builder()
                .url(url.toStringUrl())

        for ((key, value) in url.headers) {
            requestBuilder.addHeader(key, value)
        }

        val request = requestBuilder.build()

        val response = client.newCall(request).execute()
        responseBody = response.body()
        if (!response.isSuccessful) {
            throw IOException("Request failed with code: " + response.code())
        }

        val contentLength = responseBody!!.contentLength()
        stream = ContentLengthInputStream.obtain(responseBody!!.byteStream(), contentLength)
        return (stream)!!
    }

    override fun cleanup() {
        if (stream != null) {
            try {
                stream!!.close()
            } catch (e: IOException) {
                // Ignored
            }

        }
        if (responseBody != null) {
            responseBody!!.close()
        }
    }

    override fun getId(): String {
        return url.cacheKey
    }

    override fun cancel() {
        // TODO: call cancel on the client when this method is called on a background thread. See #257
    }
}
