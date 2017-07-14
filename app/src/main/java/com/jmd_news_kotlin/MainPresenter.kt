package com.jmd_news_kotlin

import android.graphics.Bitmap
import android.os.Environment
import com.jmd_news_kotlin.base.BasePresenter
import com.jmd_news_kotlin.utils.RetrofitHelper
import com.safframework.log.L
import com.zxy.tiny.Tiny
import id.zelory.compressor.Compressor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import top.zibin.luban.Luban
import top.zibin.luban.OnCompressListener
import java.io.File
import javax.inject.Inject


class MainPresenter @Inject constructor(mRetrofitHelper: RetrofitHelper) : BasePresenter<MainContract.View>(),
        MainContract.Presenter {
    //private lateinit var file: File
    private var retrofitHelper: RetrofitHelper = mRetrofitHelper

    override fun getMsg() {
        L.i("*******************************************************")
    }

    private fun luBan(file: File) {
        Luban.with(App.context)
                .load(file)                     //传人要压缩的图片
                .setCompressListener(object : OnCompressListener { //设置回调
                    override fun onStart() {
                        // TODO 压缩开始前调用，可以在方法内启动 loading UI
                    }

                    override fun onSuccess(file: File) {
                        // TODO 压缩成功后调用，返回压缩后的图片文件
                    }

                    override fun onError(e: Throwable) {
                        // TODO 当压缩过程出现问题时调用
                    }
                }).launch()    //启动压缩
    }

    private fun normalCompressorFile(file: File): File {
        return Compressor(App.context).compressToFile(file)
    }

    private fun normalCompressorBitmap(file: File): Bitmap {
        return Compressor(App.context).compressToBitmap(file)
    }

    private fun customCompressor(file: File): File {
        return Compressor(App.context)
                .setMaxWidth(640)
                .setMaxHeight(480)
                .setQuality(75)
                .setCompressFormat(Bitmap.CompressFormat.WEBP)
                .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_PICTURES).absolutePath)
                .compressToFile(file)
    }

    private fun rxjavaCompressor(file: File): File? {
        var compressoredFile: File? = null
        Compressor(App.context)
                .compressToFileAsFlowable(file)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ file -> compressoredFile = file }, { throwable -> throwable.printStackTrace(); })
        return compressoredFile
    }


    private fun tinyFile(source: String): String {
        var str = ""
        val options = Tiny.FileCompressOptions()
        Tiny.getInstance().source(source).asFile().withOptions(options).compress { isSuccess, outfile ->
            if (isSuccess) {
                str = outfile
            }
        }
        return str
    }

    private fun tinyBitmap(source: String): Bitmap? {
        var mBitmap: Bitmap? = null
        val options = Tiny.BitmapCompressOptions()
        //options.height = xxx;//some compression configuration.
        Tiny.getInstance().source("").asBitmap().withOptions(options).compress { isSuccess, bitmap ->
            if (isSuccess) {
                mBitmap = bitmap
            }
        }
        return mBitmap
    }
}