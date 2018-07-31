package com.national.security.community.DesignPattern.Factory.FactoryMethod;

/**
 * @description: 具体工厂角色 创建宝马320li车对象
 * @author: ljn
 * @time: 2018/7/30
 */
public class Bmw320liFactory implements AbstractFactory {
    @Override
    public LuxuryCar createCar() {
        return new Bmw320li();
    }
}
