package com.national.security.community.utils;

import android.util.Log;

import com.national.security.community.Config;

public class ExceptionUtil extends RuntimeException {

    private static final int ERROR_TOKEN = 100;
    private static final int ERROR_NETWORK = ERROR_TOKEN + 1;
    private static final int ERROR_SERVER = ERROR_NETWORK + 1;

    public ExceptionUtil(int code) {
        this(getErrorMessage(code));
    }

    public ExceptionUtil(String msg) {
        super(msg);
        Log.i(Config.TAG, "ExceptionUtil: " + msg);
    }

    private static String getErrorMessage(int code) {
        String msg;
        switch (code) {
            case ERROR_TOKEN:
                msg = "token过期，请重新登陆！";
                break;
            case ERROR_NETWORK:
                msg = "网络错误，请检查网络连接设置！";
                break;
            case ERROR_SERVER:
                msg = "服务器内部错误，请稍后再试！";
                break;
            default:
                msg = "未知错误！";
                break;
        }
        return msg;
    }
}
