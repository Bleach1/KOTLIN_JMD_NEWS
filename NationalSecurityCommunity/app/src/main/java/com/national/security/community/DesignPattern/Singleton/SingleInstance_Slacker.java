package com.national.security.community.DesignPattern.Singleton;

/**
 * @description: 懒汉式单例--简单同步
 * @author: ljn
 * @time: 2018/7/30
 */
public class SingleInstance_Slacker {

    private SingleInstance_Slacker() {
    }

    private static SingleInstance_Slacker instance;

    public static synchronized SingleInstance_Slacker getInstance() {
        if (instance == null) {
            instance = new SingleInstance_Slacker();
        }
        return instance;
    }


}
