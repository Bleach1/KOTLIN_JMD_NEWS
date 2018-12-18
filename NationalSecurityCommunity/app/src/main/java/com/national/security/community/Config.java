package com.national.security.community;

import android.os.Environment;

import java.io.File;

/**
 * @ description: 配置
 * @ author: ljn
 * @ time: 2017/12/7
 */
public interface Config {

    String SHARED_PREFERENCES_NAME = "name";
    String TAG = "ljn";
    String PATH_DATA = App.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";

    String PATH_CACHE = PATH_DATA + "/NetCache";

    String PATH_SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath()
            + File.separator
            + "guoAn" + File.separator + "sheQu";

    String SAVE_PHOTO_PATH = PATH_DATA + "/img_path";
    String DB_NAME = "myRealm.realm";

    String VIDEO_FILE = Environment.getExternalStorageDirectory().getAbsolutePath()
            + File.separator + "Video_Ljn";
}
