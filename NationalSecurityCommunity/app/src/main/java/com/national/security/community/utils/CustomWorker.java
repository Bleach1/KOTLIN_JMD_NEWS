package com.national.security.community.utils;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import androidx.work.Constraints;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;

/**
 * @description: 1.https://mp.weixin.qq.com/s/UKgPtJyMF8CJ8ffUuwCCvg
 * @author: ljn
 * @time: 2018/5/16
 */
public class CustomWorker extends Worker {

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void test() {
        //2. https://developer.android.google.cn/topic/libraries/architecture/workmanager
        //https://blog.csdn.net/guiying712/article/details/80386338
        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(CustomWorker.class).build();
        Objects.requireNonNull(WorkManager.getInstance()).enqueue(workRequest);
       /* WorkManager.getInstance().getStatusById(workRequest.getId())
                .observe(lifecycleOwner, workStatus -> {
                    // Do something with the status
                    if (workStatus != null && workStatus.getState().isFinished()) {
                    }
                });*/


// Create a Constraints that defines when the task should run
        Constraints myConstraints = new Constraints.Builder()
                .setRequiresDeviceIdle(true)
                .setRequiresCharging(true)
                // Many other constraints are available, see the
                // Constraints.Builder reference
                .build();

// ...then create a OneTimeWorkRequest that uses those constraints
        OneTimeWorkRequest compressionWork =
                new OneTimeWorkRequest.Builder(CustomWorker.class)
                        .setConstraints(myConstraints)
                        .build();


        Objects.requireNonNull(WorkManager.getInstance()).cancelWorkById(compressionWork.getId());

    }

    /**
     * 重复任务
     */
    public void repeat() {
        PeriodicWorkRequest.Builder photoWorkBuilder = new PeriodicWorkRequest.Builder(
                CustomWorker.class,
                12,
                TimeUnit.HOURS);
        PeriodicWorkRequest photoWork = photoWorkBuilder.build();
        Objects.requireNonNull(WorkManager.getInstance()).enqueue(photoWork);
    }

    public void taskOrder() {
       /* WorkManager.getInstance()
                .beginWith(workA)
                .then(workB)
                .then(workC)
                .enqueue();*/
    }

    @NonNull
    @Override
    public Result doWork() {
        return Result.SUCCESS;
    }
}
