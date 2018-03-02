package com.national.security.community.utils.thread_pool;

import android.util.Log;

import com.national.security.community.Config;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class DefaultRejectedExecutionHandler implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        Log.i(Config.TAG, "rejectedExecution: " + "error");
    }
}
