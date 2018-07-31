package com.national.security.community.DesignPattern.Singleton;

/**
 * @description: 懒汉式单例--双重校验
 * 在java1.4及以前版本中，很多JVM对于volatile关键字的实现的问题，会导致“双重检查加锁”的失败，
 * 因此“双重检查加锁”机制只只能用在java5及以上的版本
 * @author: ljn
 * @time: 2018/7/30
 */
public class SingleInstance_Slacker2 {

    private SingleInstance_Slacker2() {
    }

    private volatile static SingleInstance_Slacker2 instance;

    public static SingleInstance_Slacker2 getInstance() {
        if (instance == null) {
            synchronized (SingleInstance_Slacker2.class) {
                if (instance == null) {
                    instance = new SingleInstance_Slacker2();
                }
            }
        }
        return instance;
    }


}
