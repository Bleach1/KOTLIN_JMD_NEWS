package com.example.javaproject;

/**
 * @Description:
 * @Author: Bleach1
 * @CreateDate: 19-12-23 下午2:13
 */
public class ThreadDemo implements ThreadTest {

    @Override
    public void runTest() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                for (int i = 0; i < 1_000_000; i++) {
                    //Thread.interrupted(),InterruptedException 会初始化中断状态
                    if (isInterrupted()) {
                        return;
                    }

                    //线程在睡觉时 被中断了 抛出InterruptedException
                    //sleep会不断检测中断状态
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        return;
                    }
                    System.out.println("Number=" + i);
                }
            }
        };
        thread.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //thread.stop();

        //标记线程中断状态
        thread.interrupt();
    }
}
