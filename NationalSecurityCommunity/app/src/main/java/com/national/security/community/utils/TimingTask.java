package com.national.security.community.utils;

import com.national.security.community.utils.thread_pool.BackgroundThreadFactory;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ description:  定时任务
 * @ author:  ljn
 * @ time:  2018/2/26
 */
public class TimingTask {

    public static void timeTask() {
        //org.apache.commons.lang3.concurrent.BasicThreadFactory
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());
        executorService.scheduleAtFixedRate(() -> {
            //do something
        }, 1, 1, TimeUnit.SECONDS);
    }

    public static ThreadPoolExecutor getThreadPoolExecutor() {
        return new ThreadPoolExecutor(5,
                200,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<>(1024),
                new BackgroundThreadFactory()
        );
    }

}
