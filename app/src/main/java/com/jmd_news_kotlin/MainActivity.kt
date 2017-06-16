package com.jmd_news_kotlin

import com.jmd_news_kotlin.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

@Suppress("UNREACHABLE_CODE")
class MainActivity : BaseActivity<MainPresenter>(), MainContract.View {
    override fun setMsg() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initEventAndData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        sample_text.text = stringFromJNI()
        App.instance?.let { toast("ahgaha") }
    }

    override fun initInject() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLayout(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
