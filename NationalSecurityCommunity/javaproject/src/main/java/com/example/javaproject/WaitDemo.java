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
