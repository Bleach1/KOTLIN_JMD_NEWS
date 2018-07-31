package com.national.security.community.DesignPattern.Factory.SimpleFactory;

/**
 * @description: 工厂类角色
 * @author: ljn
 * @time: 2018/7/30
 */
public class DriverFactory {

    public static Car driverCar(String params) throws Exception {
        if ("Benz".equals(params)) {
            return new BenzCar();
        } else if ("Bmw".equals(params)) {
            return new BmwCar();
        } else {
            throw new Exception();
        }
    }

}
