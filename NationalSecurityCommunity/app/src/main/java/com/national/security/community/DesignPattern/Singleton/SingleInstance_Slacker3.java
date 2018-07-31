package com.national.security.community.DesignPattern.Singleton;

/**
 * @description: 懒汉式单例--静态内部类
 * @author: ljn
 * @time: 2018/7/30
 */
public class SingleInstance_Slacker3 {

    private SingleInstance_Slacker3() {
    }

    private static class SingletonHolder {
        private static SingleInstance_Slacker3 singleInstanceSlacker3 = new SingleInstance_Slacker3();
    }

    public static SingleInstance_Slacker3 getInstance() {
        return SingletonHolder.singleInstanceSlacker3;
    }
}
