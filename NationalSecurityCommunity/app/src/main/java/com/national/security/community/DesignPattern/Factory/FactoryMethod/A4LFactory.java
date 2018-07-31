package com.national.security.community.DesignPattern.Factory.FactoryMethod;

/**
 * @description: 具体工厂角色 创建奥迪A4L车对象
 * @author: ljn
 * @time: 2018/7/30
 */
public class A4LFactory implements AbstractFactory {
    @Override
    public LuxuryCar createCar() {
        return new A4L();
    }
}
