package com.example.javaproject;

/**
 * @Description:
 * @Author: Bleach1
 * @CreateDate: 19-12-23 下午3:04
 */
public class WaitDemo implements ThreadTest {

    private String str;

    private synchronized void initString() {
        str = "LJN";
        notifyAll();
    }

    /*
     * wait 释放锁进入等待队列
     *
     *
     * */
    private synchronized void printString() {
        while (str == null) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("String=" + str);
    }

    @Override
    public void runTest() {
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                printString();
            }
        };
        thread1.start();

        Thread thread2 = new Thread() {
            @Override
            public void run() {
                super.run();

                //让出自己的时间片  给同级别线程
                Thread.yield();

                //代码.........
                //强行插入
                try {
                    thread1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //代码...
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                initString();
            }
        };
        thread2.start();
    }
}
