package com.national.security.community.DesignPattern.Singleton;

/**
 * @description: 饿汉式单例
 * @author: ljn
 * @time: 2018/7/30
 */
public class SingleInstance_Hungrier {

    private SingleInstance_Hungrier() {
    }

    private static final SingleInstance_Hungrier INSTANCE = new SingleInstance_Hungrier();

    public static SingleInstance_Hungrier getInstance() {

        return INSTANCE;
    }


}
