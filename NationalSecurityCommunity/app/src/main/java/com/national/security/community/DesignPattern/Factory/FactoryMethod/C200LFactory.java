package com.national.security.community.DesignPattern.Factory.FactoryMethod;

/**
 * @description: 具体工厂角色 创建奔驰c200L车对象
 * @author: ljn
 * @time: 2018/7/30
 */
public class C200LFactory implements AbstractFactory {
    @Override
    public LuxuryCar createCar() {
        return new C200L();
    }
}
