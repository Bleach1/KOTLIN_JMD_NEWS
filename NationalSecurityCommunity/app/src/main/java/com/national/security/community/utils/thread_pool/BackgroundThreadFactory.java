package com.national.security.community.utils.thread_pool;

import android.support.annotation.NonNull;

import java.util.concurrent.ThreadFactory;

public class BackgroundThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(@NonNull Runnable r) {
        Thread thread = new Thread(r);
        thread.setName("Taoism one, two life, two three, three things");
        return thread;
    }
}
