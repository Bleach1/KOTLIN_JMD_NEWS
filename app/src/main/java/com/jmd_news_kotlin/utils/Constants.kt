package com.jmd_news_kotlin.utils

import com.jmd_news_kotlin.App
import java.io.File

object Constants {
    val BASEURL = "https://www.baidu.com"
    val PATH_DATA = App.instance?.cacheDir?.absolutePath + File.separator + "data"
    val PATH_CACHE = PATH_DATA + "/NetCache"
    val DB_NAME = "myRealm.realm"
    val ACTION_INIT = "action_init"
}