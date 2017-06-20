package com.jmd_news_kotlin

import com.jmd_news_kotlin.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity<MainPresenter>(), MainContract.View {
    override fun setMsg() {
        print("ljn")
    }

    override fun initEventAndData() {
        sample_text.text = stringFromJNI()
        mPresenter?.getMsg()
    }

    override fun initInject() {
        getActivityComponent().inject(this)
    }

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }
}
